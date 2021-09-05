package com.example.backend2lab.security;

import com.example.backend2lab.domain.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    private Optional<Account> getPrincipal(HttpServletRequest req) {
        try {
            return Optional.of(objectMapper.readValue(req.getInputStream().readAllBytes(), Account.class));
        } catch (IOException e) {
            LOG.info("Unable to fetch user from request");
            return Optional.empty();
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //return super.attemptAuthentication(request, response);
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        log.info("Username is: {}", username);
//        log.info("Password is: {}", password);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        return authenticationManager.authenticate(token);
        return getPrincipal(request).map(user -> authenticationManager.authenticate(new PreAuthenticatedAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        ))).orElse(null);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
//        User user = (User)auth.getPrincipal();
        Account account = (Account)auth.getPrincipal();
        response.getWriter().write(jwtIssuer.generateToken(account));
    }
}
