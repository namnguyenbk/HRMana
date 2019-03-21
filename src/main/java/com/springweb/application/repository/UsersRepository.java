package com.springweb.application.repository;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springweb.application.model.UsersEntity;



@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String>{

	UsersEntity findByUsername(String username);
	UsersEntity  findByEmail(String email);
	//Ham tim nhung user chua verify
	List<UsersEntity> findByStatus(String status);
	UsersEntity findByTokenEmail(String tokenEmail);
	boolean existsByUsername(String username);
}
