package com.flightradarmsn.flightradar.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/*
 * Utilizado para carregar as informacoes do usuario
 * para realizar o cadastro
 *
 * Name - Nome do usuario
 * Birth Date - Data de nascimento
 * Email - Email do usuario (utilizado como Username no Spring Security)
 * Password - Senha do usuario
 * */

public class RegisterDTO implements Serializable {
    private String name;
    private LocalDate birthDate;
    private String email;
    private String password;

    public RegisterDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDTO that = (RegisterDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(birthDate, that.birthDate) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, email, password);
    }
}
