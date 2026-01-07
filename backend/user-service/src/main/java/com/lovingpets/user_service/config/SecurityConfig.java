package com.lovingpets.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public SecurityConfig(JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(jwtAuthenticationProvider);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            AuthenticationManager authenticationManager
    ) {
        return new JwtAuthenticationFilter(authenticationManager);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                HttpMethod.GET, "/users/me"
                        ).hasAnyRole("CLIENT", "ADMIN", "EMPLOYEE")
                        .requestMatchers(
                                HttpMethod.PATCH, "/users/me"
                        ).hasAnyRole("CLIENT", "ADMIN", "EMPLOYEE")
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
                )
                .addFilterBefore(
                        jwtAuthenticationFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }


}
