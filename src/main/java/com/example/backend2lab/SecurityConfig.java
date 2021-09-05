package com.example.backend2lab;

import com.example.backend2lab.security.LoginAuthorizationFilter;
import com.example.backend2lab.persistance.AccountRepository;
import com.example.backend2lab.security.JWTIssuer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Created by Karl Danielsson - JAVA 20B
 * Date: 2021-09-05
 * Time: 12:48
 * Project: backend2Lab
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AccountRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTIssuer jwtIssuer;

    @Autowired
    public SecurityConfig(AccountRepository userRepo, PasswordEncoder passwordEncoder, JWTIssuer jwtIssuer) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtIssuer = jwtIssuer;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userRepo::findByUsername)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final LoginAuthorizationFilter filter = new LoginAuthorizationFilter(authenticationManager(), jwtIssuer, new ObjectMapper());
//        final JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(authenticationManager(), jwtIssuer);
//        http
//                .csrf().disable()
//                .cors()
//                .disable()
//                .authorizeRequests()
//                //.antMatchers("/login/*").permitAll()
//
//                .anyRequest().authenticated().and()
//                .addFilter(filter)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(filter);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
