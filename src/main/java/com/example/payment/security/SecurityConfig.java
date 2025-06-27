package com.example.payment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // отключает защиту от CSRF для удобства
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // разрешает все запросы без логина
        return http.build();
    }
}
