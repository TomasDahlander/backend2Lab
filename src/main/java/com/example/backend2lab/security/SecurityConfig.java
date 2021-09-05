package com.example.backend2lab.security;

import com.example.backend2lab.filter.LoginAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Created by Karl Danielsson - JAVA 20B
 * Date: 2021-09-05
 * Time: 12:48
 * Project: backend2Lab
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/login/*").permitAll()
                .anyRequest().authenticated().and()
                .addFilter(new LoginAuthorizationFilter(authenticationManagerBean()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
    }
}
