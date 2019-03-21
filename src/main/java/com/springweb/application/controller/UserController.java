package com.springweb.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/register")
	public Map<String, Object> register(@RequestBody UsersEntity user){
		Map<String, Object> mapResponse=new HashMap<>();
		if(user.getEmail()==null) {
			mapResponse.put("validation", "Bạn chưa có email!Vui lòng nhập email");
			return  mapResponse;
		}
		if(user.getUsername()==null) {
			mapResponse.put("validation", "Bạn chưa có username!Vui lòng nhập username");
			return  mapResponse;
		}
		if(user.getPassword()==null) {
			mapResponse.put("validation", "Bạn chưa có password!Vui lòng nhập password");
			return  mapResponse;
		}
		
		
		if(userRepository.existsByUsername(user.getUsername())==true) {
			mapResponse.put("error", "Đã tồn tại tài khoản");
			return  mapResponse;
		}
		
		user.setPassword(encoder.encode(user.getPassword()));
		RoleEntity roleEntity=roleRepository.findById(Integer.valueOf(2)).get();
		user.setRole(roleEntity);
		try {
			userRepository.save(user);
		} catch (Exception e) {
			mapResponse.put("error", "Có lỗi xảy ra");
			return  mapResponse;
		}
		mapResponse.put("success", "Thêm tài khoản thành công");
		return  mapResponse;
	}

}
