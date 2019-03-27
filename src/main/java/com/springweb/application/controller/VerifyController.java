package com.springweb.application.controller;

import java.util.HashMap;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springweb.application.model.UsersEntity;
import com.springweb.application.sevice.UsersSevice;

@RestController
public class VerifyController {

	@Autowired
	UsersSevice userService ;

	@RequestMapping("/verify")
	public Map<String, Object> verify(@RequestParam("verify_code") String verifyCode){
	UsersEntity user=userService.getUserByToken(verifyCode);
	Map<String, Object> map=new HashMap<String, Object>();
	if(user==null) {
		map.put("code", "1401");
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
