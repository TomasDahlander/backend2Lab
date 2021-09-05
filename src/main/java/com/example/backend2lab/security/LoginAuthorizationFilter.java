package com.example.backend2lab.security;

import com.example.backend2lab.domain.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Karl Danielsson - JAVA 20B
 * Date: 2021-09-05
 * Time: 13:08
 * Project: backend2Lab
 */
public class LoginAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(LoginAuthorizationFilter.class);

    private AuthenticationManager authenticationManager;
    private JWTIssuer jwtIssuer;
    private ObjectMapper objectMapper;

    public LoginAuthorizationFilter(final AuthenticationManager authenticationManager, final JWTIssuer jwtIssuer, final ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtIssuer = jwtIssuer;
        this.objectMapper = objectMapper;
    }

    private Optional<User> getPrincipal(HttpServletRequest req) {
        try {
            return Optional.of(objectMapper.readValue(req.getInputStream().readAllBytes(), User.class));
        } catch (IOException e) {
            LOG.info("Unable to fetch user from request");
            return Optional.empty();
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return getPrincipal(request).map(user -> authenticationManager.authenticate(new PreAuthenticatedAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        ))).orElse(null);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        User user = (User)auth.getPrincipal();
        response.getWriter().write(jwtIssuer.generateToken(user));
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null) {
            // parse the token.
            User user = jwtIssuer.validate(token.substring(7));

            if (user != null) {
                // new arraylist means authorities
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }

            return null;
        }

        return null;
    }
}
