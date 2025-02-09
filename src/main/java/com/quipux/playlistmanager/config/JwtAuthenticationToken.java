package com.quipux.playlistmanager.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String token;

    public JwtAuthenticationToken(final String token) {
        super(null, null);
        this.token = token;
        setAuthenticated(false);
    }
}
