package com.springweb.application.controller;

import java.util.HashMap;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springweb.application.model.UsersEntity;
import com.springweb.application.sevice.UsersSevice;

public class VerifyController {

	@Autowired
	UsersSevice userService ;
	@RequestMapping("/verify:{token}")
	public Map<String, Object> verify(@PathVariable String token){
	UsersEntity user=userService.getUserByToken(token);
	Map<String, Object> map=new HashMap<String, Object>();
	if(user==null) {
		map.put("error", "Lỗi xác thực");
	}else {
		user.setStatus("VERIFY");
		try {
			userService.save(user);
			map.put("code", "1000");
		} catch (Exception e) {
			map.put("code", "888");
			e.printStackTrace();
		}
		
	}
		return map;
	}
}
