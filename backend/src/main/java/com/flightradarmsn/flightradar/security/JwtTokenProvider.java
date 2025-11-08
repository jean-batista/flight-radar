package com.flightradarmsn.flightradar.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.flightradarmsn.flightradar.model.dto.TokenDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {
    
    // Chave Secreta definida por variavel de ambiente
    @Value("${security.jwt.token.secret-key}")
    private String key;

    // O tempo de expiracao do token
    @Value("${security.jwt.token.expire-length}")
    private Long validityTimeInMilliseconds;

    // User details service
    @Autowired
    UserDetailsService userDetailsService;

    // Algoritmo usado
    Algorithm algorithm = null;

    /*
    * Metodo de inicializacao que roda automaticamente uma unica vez quando
    * a aplicacao e iniciada
    *
    * O objetivo dele é preparar a chave secreta (key) e o algoritmo (algorithm)
    * que serão usados pela sua classe JwtTokenProvider para criar e validar todos os Tokens JWT
    * */
    @PostConstruct
    protected void init() {
        this.key = Base64.getEncoder().encodeToString(key.getBytes());
        this.algorithm = Algorithm.HMAC256(key.getBytes());
    }

    /*
    * Cria o token
    * */
    public TokenDTO createToken(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityTimeInMilliseconds);
        String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        String token = JWT.create()
        .withClaim("roles", roles)
        .withIssuedAt(now)
        .withExpiresAt(validity)
        .withSubject(username)
        .withIssuer(issueUrl)
        .sign(algorithm);

        return new TokenDTO(username, token);
    }

    /*
    * Atualiza o token a partir de um token valido
    * */
    public TokenDTO refreshToken(String refreshToken) {
        var token = "";
        if(refreshToken.contains("Bearer ")) token = refreshToken.substring("Bearer ".length());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        // Extrai do Token o username/authorities/roles
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

        return createToken(username, roles);
    }

    /*
    * pega a string do token que veio na requisicao, valida, e a transforma em um objeto
    * Authentication que o Spring Security usa para entender quem esta logado e quais permissoes essa pessoa tem
    * */
    public Authentication getAuthentication(String token) {
        Algorithm alg = Algorithm.HMAC256(key.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /*
    * Metodo responsavel por validar o token
    * */
    public boolean validateToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        try {
            if(decodedJWT.getExpiresAt().before(new Date())) return false;
            return true;
        } catch(Exception e) {
            throw new RuntimeException("Expired or Invalid JWT Token");
        }
    }

}
