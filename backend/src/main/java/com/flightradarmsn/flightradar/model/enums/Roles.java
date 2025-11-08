package com.flightradarmsn.flightradar.model.enums;

import org.springframework.security.core.GrantedAuthority;

/*
* Representa os papeis que um usuario pode ter no sistema
* */

public enum Roles implements GrantedAuthority {
    
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }

}
