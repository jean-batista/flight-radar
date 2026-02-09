package com.flightradarmsn.flightradar.model.dto;

import com.flightradarmsn.flightradar.validations.annotations.AgeValidation;
import jakarta.validation.constraints.*;

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

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado")
    @AgeValidation(message = "A idade mínima é de 18 anos")
    private LocalDate birthDate;

    @NotBlank(message = "O email é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 4, message = "A senha deve possuir pelo menos 8 caracteres")
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
