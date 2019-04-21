package com.springweb.application.controller;

import com.springweb.application.model.RoleEntity;
import com.springweb.application.model.UsersEntity;
import com.springweb.application.repository.RoleRepository;
import com.springweb.application.repository.UsersRepository;
import com.springweb.application.sevice.EmailService;
import com.springweb.application.sevice.UsersSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class UserController {

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UsersRepository userRepository;
	@Autowired
	EmailService emailService;
	@Autowired
	UsersSevice usesService;

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

		if(userRepository.existsByUsername(user.getUsername())) {
			mapResponse.put("code", "1100");
			return  mapResponse;
		}
		
		user.setPassword(encoder.encode(user.getPassword()));
		RoleEntity roleEntity=roleRepository.findById(Integer.valueOf(2)).get();
		user.setRole(roleEntity);
			user.setStatus("UNVERIFY");
		try {
			UUID uuid=UUID.randomUUID();
			String token=uuid.toString();
			user.setTokenEmail(token);
			Map<String, Object> res =  sendEmail(user, token) ;
			if ( res.get("code").equals("1000")){
				mapResponse.put("code", "1000");
				userRepository.save((UsersEntity) res.get("userEntity"));
			}else {
				mapResponse.put("code", "1103");
			}
		} catch (Exception e) {
			mapResponse.put("code", "1002");
			e.printStackTrace();
			return  mapResponse;
		}
		return  mapResponse;
	}

	@PostMapping("/getStatusUser")
	public Map<String, Object> getStatusUser(@RequestBody UsersEntity username){
		UsersEntity userData = null;
		try {
			userData = userRepository.findByUsername(username.getUsername());
			Map<String, Object> status = new HashMap<>();
			if (userData.getStatus() != null){
				status.put("status", userData.getStatus());
			}else {
				status.put("status", "NOT_VERIFY");
			}
			 return status;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public Map<String, Object> sendEmail( UsersEntity user, String token){
		StringBuilder msgContent=new StringBuilder("Verify http://localhost:9000/verify:");
		Map<String, Object> res = new HashMap<>();

		msgContent.append(token);
		int result;
		result = emailService.sendEmailTo(user.getEmail(), msgContent.toString());
		if(result==1) {
			res.put("code", "1000");
			res.put("userEntity", user);
		}
		else{
			res.put("code", "1002");
		}
		return res;

	}

	@PostMapping("/getUserDetailInfo")
	public Map<String, Object> getUserInfoDetail(@RequestBody UsersEntity username){
		UsersEntity user = null;
		try {
			user = userRepository.findByUsername(username.getUsername());
			Map<String, Object> userInfo = new HashMap<>();
			if (user != null){
				userInfo.put("username", user.getUsername());
				userInfo.put("email", user.getEmail());
				userInfo.put("status", user.getStatus());
				userInfo.put("roleId", user.getRole().getId());
				if(user.getFirstname() != null){
					userInfo.put("fname", user.getFirstname());
				}
				if(user.getLastname() != null){
					userInfo.put("lname", user.getLastname());
				}
				return userInfo;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
