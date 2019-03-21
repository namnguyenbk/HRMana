package com.springweb.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.springweb.application.model.RoleEntity;
import com.springweb.application.model.UsersEntity;
import com.springweb.application.repository.RoleRepository;
import com.springweb.application.repository.UsersRepository;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@Configurable
public class SpringWebApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UsersRepository userRepository;
	
	@Bean
	public void addAdmin() {
		
		RoleEntity roleAdmin=roleRepository.findByName("admin");
		if(roleAdmin==null) {
			try {
				roleAdmin=new RoleEntity(1, "admin", "Phân quyền admin");
				roleRepository.save(roleAdmin);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RoleEntity  roleUser=roleRepository.findByName("user");
		if(roleUser==null) {
			try {
			 roleUser=new RoleEntity(2, "user", "Phân quyền user");
				roleRepository.save(roleUser);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		UsersEntity admin=userRepository.findByEmail("admin@gmail.com");
		if(admin==null) {
			 admin=new UsersEntity();
				admin.setEmail("admin@gmail.com");
				admin.setUsername("admin");
				admin.setFirstname("Huyền");
				admin.setLastname("Trần");
				admin.setPassword(encoder.encode("12345678@Abc"));
				admin.setStatus("VERIFY");
				admin.setRole(roleAdmin);
				userRepository.save(admin);
		}
		
		
	}

}

