package com.springweb.application.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User{
	private static final long serialVersionUID = 1L;
public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

private String id;
private String email;
private static Collection<GrantedAuthority> getAuthorities(UsersEntity user){
	Collection<GrantedAuthority> grantedAuthorities=new ArrayList<>();
	if(user.getRole()!=null) {
		
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
	}
	return grantedAuthorities;
}

public CustomUser(UsersEntity user) {
	super(user.getUsername(), user.getPassword(), getAuthorities(user));
	this.id=user.getId();
	this.email=user.getEmail();
}
}
