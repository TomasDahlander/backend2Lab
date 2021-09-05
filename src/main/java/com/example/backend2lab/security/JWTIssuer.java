package com.example.backend2lab.security;

import com.example.backend2lab.domain.model.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karl Danielsson - JAVA 20B
 * Date: 2021-09-05
 * Time: 12:57
 * Project: backend2Lab
 */
public class JWTIssuer {

    private final Key key;
    private final Duration validity;

    public JWTIssuer(final Key key, final Duration validity) {
        this.key = key;
        this.validity = validity;
    }

    public String generateToken(final Account account) {
        final String authorities = account.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(account.getUsername())
                .claim("authorities", authorities)
                .signWith(key)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(validity)))
                .compact();
    }

//    public Account validate(String token) {
//        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//
//        String authorities = (String) claims.get("authorities");
//        List<String> roles = Arrays.stream(authorities.split(",")).map(r -> r.substring(5)).collect(Collectors.toList());
//        return new Account(claims.getSubject(), null, roles);
//    }
}
