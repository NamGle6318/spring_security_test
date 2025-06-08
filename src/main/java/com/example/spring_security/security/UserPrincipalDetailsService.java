package com.example.spring_security.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring_security.entity.User;
import com.example.spring_security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class UserPrincipalDetailsService implements UserDetailsService{

    
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: "+username);
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);
        
        return userPrincipal;
    }
    
}
