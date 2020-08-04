package com.practice.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/info*").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN") // Admin 롤 받은상태
                .anyRequest().authenticated();

        http.formLogin(); // 로그인
        http.httpBasic(); // 기본
    }
}
