package ru.panic.springsteamauthexample.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public record UserDetails(String id) implements org.springframework.security.core.userdetails.UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return id;
    }
}
