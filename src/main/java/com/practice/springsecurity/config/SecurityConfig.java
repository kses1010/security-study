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
        http.authorizeRequests(requests -> requests.anyRequest().authenticated());

        http.formLogin()
                .loginPage("/loginPage")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .usernameParameter("userId")
                .passwordParameter("secret")
                .loginProcessingUrl("/login-proc")
                .successHandler((request, response, authentication) -> {
                    System.out.println("authentication " + authentication.getName());
                    response.sendRedirect("/");
                })
                .failureHandler(((request, response, exception) -> {
                    System.out.println("exception " + exception.getMessage());
                    response.sendRedirect("/login");
                }))
                .permitAll();
    }
}
