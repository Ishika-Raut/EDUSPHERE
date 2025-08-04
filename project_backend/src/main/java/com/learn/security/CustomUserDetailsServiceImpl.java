package com.learn.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.entities.User;
import com.learn.repository.UserRepository;

import lombok.AllArgsConstructor;


@Service //springbean containing business logic
@Transactional
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	private final UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//invoke dao's method
		User user=userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Invalid Email"));
		return user;
	}

}
