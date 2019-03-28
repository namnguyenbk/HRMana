package com.springweb.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class RoleEntity {

	@Id
	@Column(name="roleID")
	private Integer id;
	
	@Column(name="name",columnDefinition="nvarchar(16)")
	private String  name ;
	
	@Column(name="description",columnDefinition="nvarchar(50)")
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="role")
	private Set<UsersEntity> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@JsonIgnore
	public Set<UsersEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UsersEntity> users) {
		this.users = users;
	}

	public RoleEntity(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public RoleEntity() {
		super();
	}
	
	
	
}
