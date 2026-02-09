package com.flightradarmsn.flightradar.exceptions;

/*
* Excecao responsavel por tratar excecoes em que recursos nao sao encontrados
* */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
