package com.springweb.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springweb.application.model.CustomUser;
import com.springweb.application.model.UsersEntity;
import com.springweb.application.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired 
	private UsersRepository usersRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersEntity userEntity=null;
		try {
			userEntity=usersRepository.findByUsername(username);
			if (userEntity!=null&& userEntity.getEmail() != null && userEntity.getUsername()!=null) {
			            CustomUser user=new CustomUser(userEntity);
			            return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
