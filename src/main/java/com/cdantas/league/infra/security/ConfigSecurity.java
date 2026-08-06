package com.cdantas.league.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Bean
    public UserDetailsService registerUserData(){
        UserDetails user1 = User.builder()
                .username("caua@gmail.com")
                .password("{noop}1234")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/css/**", "/js/**", "/assets/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/entrar").permitAll()  // <---
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/entrar")          // página de login (GET)
                        .loginProcessingUrl("/login")  // endpoint do POST
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/entrar?logout")
                        .permitAll()
                )
                .build();
    }
}