package com.example.spring_security.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.entity.User;
import com.example.spring_security.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@Log4j2
public class IndexController {
    
    private final UserRepository userRepository;

    @GetMapping("/")
    public String getIndex() {
        return "Good";
    }

    @GetMapping("/auth")
    public Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return authentication;
    }

    @GetMapping("/test1")
    public String test1() {
        return "API Test 1";
    }
    @GetMapping("/test2")
    public String test2() {
        return "API Test 2";
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }
}
