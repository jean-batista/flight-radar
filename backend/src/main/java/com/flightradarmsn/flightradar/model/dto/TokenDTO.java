package com.flightradarmsn.flightradar.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/*
* Utilizado para carregar as informacoes do token
*
* Username - Username do usuario
* Token - Token de autenticacao
* */

public class TokenDTO implements Serializable {

    @NotBlank(message = "O usuário é obrigatório")
    private String username;

    @NotBlank(message = "O token é obrigatório")
    private String token;

    @NotBlank(message = "A lista de roles não pode estar vazia")
    private List<String> roles;

    public TokenDTO() {
    }

    public TokenDTO(String username, String token, List<String> roles) {
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TokenDTO tokenDTO = (TokenDTO) o;
        return Objects.equals(username, tokenDTO.username) && Objects.equals(token, tokenDTO.token) && Objects.equals(roles, tokenDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, token, roles);
    }

}
