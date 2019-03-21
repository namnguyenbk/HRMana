package com.springweb.application.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springweb.application.model.UsersEntity;
import com.springweb.application.repository.UsersRepository;

@Service
public class UsersServiceIml implements UsersSevice {

	@Autowired
	UsersRepository userRepository;
	@Override
	public List<UsersEntity> getUserUnverify() {
		// TODO Auto-generated method stub
		List<UsersEntity> lst=new ArrayList<>();
		try {
			lst=userRepository.findByStatus("UNVERIFY");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	@Override
	public UsersEntity getUserByToken(String token) {
		
		return userRepository.findByTokenEmail(token);
	}
	@Override
	public void save(UsersEntity user) {
		userRepository.save(user);
		
	}

}
