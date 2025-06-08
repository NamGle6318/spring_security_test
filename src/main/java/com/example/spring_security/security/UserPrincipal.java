package com.example.spring_security.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring_security.entity.User;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    // 계정의 권한 목록을 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of permissions (name)
        this.user.getPermissionList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });

        // Extract list of permissions (ROLE_name)
        this.user.getRoleList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + p);
            authorities.add(authority);
        });

        return authorities;
        
    }

    @Override
    // 계정의 비밀번호를 리턴
    public String getPassword() {
        
        return this.user.getPassword();
    }

    @Override
    // 계정의 고유한 값을 리턴
    public String getUsername() {
        
        return this.user.getUsername();
    }
    @Override
    // 계정의 만료 여부 리턴
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // 계정의 잠김 여부 리턴
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // 비밀번호 만료 여부 리턴
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // 계정의 활성화 여부 리턴
    public boolean isEnabled() {
        return this.user.getActive() == 1;
    }
}
