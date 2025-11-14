package com.flightradarmsn.flightradar.service;

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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
        PersonDTO person = new PersonDTO();
        person.setName(data.getName());
        person.setEmail(data.getEmail());
        person.setBirthDate(data.getBirthDate());
        person = personService.save(person);
        userService.save(data, person);
        logger.info("Usuario cadastrado com sucesso");
    }

    /*
    * Metodo de login
    * */
    public TokenDTO signIn(AccountCredentials credentials) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
        );
        var entity = userService.findByUsername(credentials.getUsername());
        if(entity == null) throw new RuntimeException("User is null");
        logger.info("Login realizado com sucesso");
        return tokenProvider.createToken(credentials.getUsername(), entity.getRoles().stream().map(Enum::name).toList());
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
        var user = userService.loadUserByUsername(username);
        if(user == null) throw new RuntimeException("User is null");
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
            if(StringUtils.isBlank(profileDTO.getCurrentPassword())) throw new RuntimeException("Senha atual vazia!");
            if(!passwordEncoder.matches(profileDTO.getCurrentPassword(), user.getPassword())) throw new RuntimeException("Senha incorreta!");
            user.setPassword(passwordEncoder.encode(profileDTO.getNewPassword()));
        }
        return ObjectMapper.parseObject(user, UserDTO.class);
    }
}
