package com.springweb.application.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springweb.application.model.UsersEntity;
import com.springweb.application.repository.UsersRepository;
import com.springweb.application.sevice.EmailService;
import com.springweb.application.sevice.UsersSevice;

@RestController
@RequestMapping("/admin")

public class AdminController {

	@Autowired
	EmailService emailService;
	
	@Autowired
	UsersSevice usesService;
	
	@GetMapping("/getUserUnverify")
	public List<UsersEntity> getUserUnverify(){
		List<UsersEntity> usersUnverify=usesService.getUserUnverify();
		return usersUnverify;
	}
	
	@PostMapping("/sendEmail")
	public Map<String, Object> sendEmail(@RequestBody UsersEntity user){
		Map<String, Object> map=new HashMap<String, Object>();
		StringBuilder msgContent=new StringBuilder("http://localhost:9000/verify:");
		UUID uuid=UUID.randomUUID();
		String token=uuid.toString();
		user.setTokenEmail(token);
		usesService.save(user);
		msgContent.append(token);
		int result=emailService.sendEmailTo(user.getEmail(), msgContent.toString());
		if(result==0) {
			map.put("error", "gửi email thất bại");
			}
		else map.put("success", "gửi email thành công");
		return map;
		
		
	}
	
	
	


	

//	@RequestMapping("/sendEmail")
//	public Map<String, String> sendEmail(@RequestParam(name="email") String emailAddress){
//	Map<String, String> map=new HashMap<>();
//	int result=emailService.sendEmailTo(emailAddress);
//	if(result==0) {
//	map.put("err", "Chua gui duoc email");
//	}else map.put("message", "gui email thanh cong vui long kiem tra email");
//
//	return map;
//	}
}
