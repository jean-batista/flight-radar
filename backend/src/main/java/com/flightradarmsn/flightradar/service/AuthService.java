package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.model.dto.*;
import com.flightradarmsn.flightradar.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
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

}
