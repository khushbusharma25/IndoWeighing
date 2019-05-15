package com.ind.weighing.Indo_weighing.config;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class JwtUser implements UserDetails {

    private final String id;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String phoneNumber;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date lastAccessTime;
    private final boolean enabled;
 

    public JwtUser(
          String id,
          String username,
          String firstname,
         
          String lastname,
          String phoneNumber,
          String email,
          String password, Collection<? extends GrantedAuthority> authorities,
          Date lastAccessTime,
          boolean enabled
    ) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.lastAccessTime=lastAccessTime;
        this.enabled = enabled;
        
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        return firstname;
    }
    
  
    public String getLastname() {
        return lastname;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

    
}
