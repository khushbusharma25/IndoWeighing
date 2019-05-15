package com.ind.weighing.Indo_weighing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ind.weighing.Indo_weighing.config.JwtUser;
import com.ind.weighing.Indo_weighing.config.JwtUserFactory;
import com.ind.weighing.Indo_weighing.domain.UserDTO;
import com.ind.weighing.Indo_weighing.repository.UserRepo;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			try {
				JwtUser user1 = JwtUserFactory.create(user);
				return user1;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		
		}		
	}
}
