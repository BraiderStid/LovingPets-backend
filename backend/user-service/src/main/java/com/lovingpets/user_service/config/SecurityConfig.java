package com.lovingpets.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                //.sessionManagement(session ->
                //        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                HttpMethod.GET, "/users", "/users/*"
                        ).hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(
                                HttpMethod.POST, "/users"
                        ).hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(
                                HttpMethod.PATCH, "/users/*"
                        ).hasAnyRole("ADMIN", "EMPLOYEE")
                        .anyRequest().authenticated()
                );

        return http.build();
    }


}
