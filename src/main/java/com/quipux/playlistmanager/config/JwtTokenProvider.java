package com.quipux.playlistmanager.config;

import com.quipux.playlistmanager.common.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${auth.jwt-secret}")
    private String jwtSecret;

    @Value("${auth.access-token-validity-minutes}")
    private Long accessTokenValidityMinutes;

    public JwtTokenProvider() {
    }

    public String createToken(final User user) {
        Claims claims = Jwts.claims().subject(user.getEmail()).build();
        Date tokenCreateTime = new Date();

        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidityMinutes));
        return Jwts.builder()
                .claims(claims)
                .expiration(tokenValidity)
                .signWith(key())
                .compact();
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(final String token) {

        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(final String token) {
        Jwts.parser()
                .verifyWith(key())
                .build()
                .parse(token);
        return true;

    }
}
