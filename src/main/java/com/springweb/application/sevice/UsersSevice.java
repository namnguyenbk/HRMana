package com.springweb.application.sevice;

import java.util.List;

import com.springweb.application.model.UsersEntity;

public interface UsersSevice {
   List<UsersEntity> getUserUnverify();

UsersEntity getUserByToken(String token);

void save(UsersEntity user);
   
}
