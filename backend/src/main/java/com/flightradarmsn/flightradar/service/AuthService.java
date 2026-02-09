package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.exceptions.AuthenticationException;
import com.flightradarmsn.flightradar.exceptions.RegisterException;
import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.*;
import com.flightradarmsn.flightradar.model.entities.Person;
import com.flightradarmsn.flightradar.model.entities.User;
import com.flightradarmsn.flightradar.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;
    
    @Autowired
    private PersonService personService;

    /*
    * Metodo de registro de usuarios
    * */
    @Transactional
    public void register(RegisterDTO data) {
        if(data == null) throw new RegisterException("O usuário não pode ser nulo");
        PersonDTO person = new PersonDTO();
        person.setName(data.getName());
        person.setEmail(data.getEmail());
        person.setBirthDate(data.getBirthDate());
        try {
            person = personService.save(person);
            userService.save(data, person);
        } catch(Exception e) {
            throw new RegisterException("Já existe um usuário com este email, por favor tente outro");
        }
        logger.info("Usuario cadastrado com sucesso");
    }

    /*
    * Metodo de login
    * */
    public TokenDTO signIn(AccountCredentials credentials) {
        if(credentials == null) throw new AuthenticationException("As credenciais não podem ser nulas");
        if(credentials.getUsername().isBlank() || credentials.getUsername() == null) {
            throw new AuthenticationException("O usuário não pode estar vazio");
        }
        if(credentials.getPassword().isBlank() || credentials.getPassword() == null) {
            throw new AuthenticationException("A senha não pode estar vazia");
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
            );
            var entity = userService.findByUsername(credentials.getUsername());
            logger.info("Login realizado com sucesso");
            return tokenProvider.createToken(credentials.getUsername(), entity.getRoles().stream().map(Enum::name).toList());
        } catch(BadCredentialsException e) {
            throw new AuthenticationException("Credenciais incorretas");
        }
    }

    /*
    * Metodo de refresh token
    *
    * Utiliza um token valido para gerar outro token
    * evitando que o usuario precise fazer login novamente
    * previnindo o trafego desnecessario de informacoes
    * sensiveis
    * */
    public TokenDTO refreshToken(String username, String refreshToken) {
        userService.loadUserByUsername(username);
        logger.info("Token atualizado com sucesso");
        return tokenProvider.refreshToken(refreshToken);
    }

    /*
     * Metodo para carregar os dados do usuario
     */
    public ProfileDTO getUserProfile() {
        // Pega o usuário logado (que o JwtTokenFilter já validou)
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ProfileDTO(
                user.getPerson().getName(),
                user.getPerson().getBirthDate(),
                user.getUsername()
        );
    }

    /*
     * Metodo para atualizar os dados do usuario
     *
     * Necessita atualizar o token, pois depois de mudar o email,
     * o token antigo fica invalido (contem o email antigo)
     */
    @Transactional
    public TokenDTO updateUserProfile(ProfileDTO profileDTO) {
        User contextUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person contextPerson = contextUser.getPerson();

        PersonDTO personToUpdate = updatePersonInformations(contextPerson, profileDTO);
        UserDTO userToUpdate = updateUserInformations(contextUser, profileDTO);

        personService.update(personToUpdate);
        UserDTO updatedUser = userService.update(userToUpdate);

        String username = updatedUser.getUsername();
        List<String> roles = updatedUser.getRoles().stream().map(Enum::toString).toList();

        logger.info("Dados atualizados com sucesso!");

        return tokenProvider.createToken(username, roles);
    }

    /*
    * Metodo para atualizar as informacoes da pessoa
    * */
    private PersonDTO updatePersonInformations(Person person, ProfileDTO profileDTO) {
        person.setName(profileDTO.getName());
        person.setEmail(profileDTO.getEmail());
        person.setBirthDate(profileDTO.getBirthDate());
        return ObjectMapper.parseObject(person, PersonDTO.class);
    }

    /*
    * Metodo para atualizar as informacoes referentes ao usuario
    * */
    private UserDTO updateUserInformations(User user, ProfileDTO profileDTO) {
        user.setUsername(profileDTO.getEmail());
        if(StringUtils.isNotEmpty(profileDTO.getNewPassword())) {
            if(StringUtils.isBlank(profileDTO.getCurrentPassword())) {
                throw new AuthenticationException("A senha é obrigatória");
            }
            if(!passwordEncoder.matches(profileDTO.getCurrentPassword(), user.getPassword())) {
                throw new AuthenticationException("Senha incorreta");
            }
            user.setPassword(passwordEncoder.encode(profileDTO.getNewPassword()));
        }
        return ObjectMapper.parseObject(user, UserDTO.class);
    }
}
