package com.springweb.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.springweb.application.model.RoleEntity;
import com.springweb.application.model.UsersEntity;
import com.springweb.application.repository.RoleRepository;
import com.springweb.application.repository.UsersRepository;


@RestController
public class UserController {

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UsersRepository userRepository;
	@RequestMapping(value="/register",method= RequestMethod.POST)
	public Map<String, Object> register(@RequestBody UsersEntity user){
		Map<String, Object> mapResponse=new HashMap<>();
		if(user.getEmail()==null) {
			mapResponse.put("code", "1001");
			mapResponse.put("invalid_data", "email");
			return  mapResponse;
		}
		if(user.getUsername()==null) {
			mapResponse.put("code", "1001");
			mapResponse.put("invalid_data", "username");
			return  mapResponse;
		}
		if(user.getPassword()==null) {
			mapResponse.put("code", "1001");
			mapResponse.put("invalid_data", "password");
			return  mapResponse;
		}
		
		
		if(userRepository.existsByUsername(user.getUsername())==true) {
			mapResponse.put("code", "1100");
			return  mapResponse;
		}
		
		user.setPassword(encoder.encode(user.getPassword()));
		RoleEntity roleEntity=roleRepository.findById(Integer.valueOf(2)).get();
		user.setRole(roleEntity);
		try {
			userRepository.save(user);
		} catch (Exception e) {
			mapResponse.put("code", "1002");
			return  mapResponse;
		}
		mapResponse.put("code", "1000");
		return  mapResponse;
	}

}
