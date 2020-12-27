package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			com.fitscorp.j2eemobileapi.restservices.restservices.entities.User user = userService.getUserByEmail(username);
			return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
		} catch (Exception e) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found", e);
		}
	}
}
