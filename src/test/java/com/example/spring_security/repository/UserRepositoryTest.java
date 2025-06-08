package com.example.spring_security.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.spring_security.config.SecurityConfig;
import com.example.spring_security.entity.User;
import com.example.spring_security.entity.UserRole;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Test
    public void insertUserText() {
        User user1 = new User("NamGle", passwordEncoder().encode("1111"), "USER,MANAGER,ADMIN", "");
        User user2 = new User("Sans", passwordEncoder().encode("1111"), "USER,MANAGER", "");
        User user3 = new User("flower", passwordEncoder().encode("1111"), "USER", "");
        
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
