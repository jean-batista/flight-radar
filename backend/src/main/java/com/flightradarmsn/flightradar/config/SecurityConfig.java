package com.flightradarmsn.flightradar.config;

import com.flightradarmsn.flightradar.security.JwtTokenFilter;
import com.flightradarmsn.flightradar.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

/*
* Classe responsavel por conter as condiguracoes de seguranca
* */

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    @Autowired
    private JwtTokenProvider tokenProvider;

    /*
    * Configura e cria o sistema de codificacao de senhas (password encoding) para o seu Spring Security,
    * */
    @Bean
    PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(encoder);
        return passwordEncoder;
    }

    /*
    * Componente que executa a logica de login (valida a senha, verifica se o usuario existe, etc.).
    * */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /*
    * Define a cadeia de filtros de seguranca, agindo como um porteiro para
    * todas as requisicoes que a API
    * */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        JwtTokenFilter filter = new JwtTokenFilter(tokenProvider);

        // Cadeia de filtros
        return http
            .httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(
                authorize -> authorize
                // Endpoints publicos
                .requestMatchers("/auth/signin", "/auth/refresh/**", "auth/register").permitAll()
                // Endpois com acesso de administrador
                .requestMatchers("/api/person/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .cors(cors -> {})
            .build();
    }

}
