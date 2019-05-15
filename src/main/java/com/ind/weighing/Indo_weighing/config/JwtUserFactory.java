package com.ind.weighing.Indo_weighing.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ind.weighing.Indo_weighing.domain.Role;
import com.ind.weighing.Indo_weighing.domain.UserDTO;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserDTO user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getfName(),
                
                user.getlName(),
                user.getContNo(),
                user.getEmail(),
                user.getPassword(),
                
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getLastAccessTime(),
                true
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
                .collect(Collectors.toList());
    }
}
