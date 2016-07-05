package com.tsystems.javaschool.uberbahn.transports.security;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserAuthority implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
