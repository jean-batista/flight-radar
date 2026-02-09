package com.flightradarmsn.flightradar.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /*
    * Filtro de seguranca principal da aplicacao
    *
    * Intercepta todas as requisicoes que chegam ao backend
    * Verificar se as requisicoes contem um Token JWT válido
    * Caso tiver um token valido, o usuario e autenticado para aquela requisicao especifica.
    * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        var token = "";
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if(bearerToken != null) {
            if(bearerToken.contains("Bearer ")) token = bearerToken.substring("Bearer ".length());
        }

        try {
            if(!token.isBlank() && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if(authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch(Exception e) {
            SecurityContextHolder.clearContext();
            request.setAttribute("access_denied_reason", e.getMessage());
        }

        filter.doFilter(request, response);
    }
    
}
