package com.example.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.spring_security.security.UserPrincipalDetailsService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity(debug = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final UserPrincipalDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable());
        
        // 1. 모든 접근 허용 시키기
        // http
        //     .authorizeHttpRequests(authorize -> authorize
        //     .requestMatchers("/").permitAll());
        
        // 2. 로그인
        http
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/").authenticated()
            .requestMatchers("/auth").permitAll());
        
        http
            .formLogin(login -> login.defaultSuccessUrl("/"));


        return http.build();
    }

    // InMemory에 유저 정보 저장하기
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user1 = User.builder()
    //     .username("user1")
    //     .password(passwordEncoder().encode("1111"))
    //     .roles("USER")
    //     .build();

    //     return new InMemoryUserDetailsManager(user1);
    // }

    // 사용자 인증
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
        .getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder())
        .and()
        .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
