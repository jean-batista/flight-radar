package com.flightradarmsn.flightradar.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightradarmsn.flightradar.model.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserDTO implements Serializable {


    private Long id;

    @NotBlank(message = "O usuário é obrigatório")
    private String username;

    @NotBlank(message = "A lista de roles não pode estar vazia")
    private List<Roles> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 4, message = "A senha deve possuir pelo menos 8 caracteres")
    private String password;

    @NotBlank(message = "Person é obrigatório")
    private PersonDTO person;
    
    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(username, userDTO.username) && Objects.equals(roles, userDTO.roles) && Objects.equals(password, userDTO.password) && Objects.equals(person, userDTO.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, roles, password, person);
    }
}
