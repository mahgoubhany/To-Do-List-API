package com.example.ToDo.config;

import com.example.ToDo.filter.JwtAuthenticationFilter;
import com.example.ToDo.repository.UserRepository;
import com.example.ToDo.security.AuthenticationService;
import com.example.ToDo.security.TaskUserDetails;
import com.example.ToDo.security.TaskUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
        return new JwtAuthenticationFilter(authenticationService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http.
                authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.GET, "/api/v1/auth/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tasks").authenticated()
                        .requestMatchers(HttpMethod.POST, "/tasks").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/tasks").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/tasks").authenticated()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }

//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        TaskUserDetailsService taskUserDetailsService = new TaskUserDetailsService(userRepository);
//        return taskUserDetailsService;
//    }

}