package com.springweb.application.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.*;


import com.springweb.application.model.Emmbeddable.ProjectMemberId;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="user")
public class UsersEntity {
	@Id
	@GenericGenerator(name="generator",strategy="guid")
	@GeneratedValue(generator="generator")
	@Column(name="userId")
	private String id;
	
	@Column(name="username",columnDefinition = "VARCHAR(40)",unique=true, nullable=false)
	private String username;
	
	@Column(name="password",columnDefinition="nvarchar(80)", nullable=false)
	private String password;
	
	@Column(name="lastname",columnDefinition="nvarchar(50)")
	private String lastname;
	
	@Column(name="avatar",columnDefinition="nvarchar(255)")
	private String avatar;
	
	@Column(name="firstname",columnDefinition="nvarchar(50)")
	private String firstname;
	
	@Column(name="email",columnDefinition="nvarchar(50)",length=50,unique=true, nullable=false)
	private String email;
	
	@Column(name="createddate")
	private Date createdDate;
	
	@Column(name="status",columnDefinition="nvarchar(50)",length=50)
	private String status;
	
	@Column(name="tokenemail",columnDefinition="nvarchar(50)",length=50)
	private String tokenEmail;
	
	@ManyToOne()
	@JoinColumn(name="roleId")
	private RoleEntity role;

	@OneToOne(mappedBy = "author")
	private TaskEntity authoredTask;

	@OneToOne(mappedBy = "assigned")
	private TaskEntity assignedTask;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	private Set<ProjectMemberEntity> teams;


	public String getId() {
		return id;
	}

	public TaskEntity getAuthoredTask() {
		return authoredTask;
	}

	public TaskEntity getAssignedTask() {
		return assignedTask;
	}

	public void setAssignedTask(TaskEntity assignedTask) {
		this.assignedTask = assignedTask;
	}

	public void setAuthoredTask(TaskEntity authoredTask) {
		this.authoredTask = authoredTask;
	}
	public Set<ProjectMemberEntity> getTeams() {
		return teams;
	}

	public void setTeams(Set<ProjectMemberEntity> teams) {
		this.teams = teams;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTokenEmail() {
		return tokenEmail;
	}

	public void setTokenEmail(String tokenEmail) {
		this.tokenEmail = tokenEmail;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public UsersEntity() {
		super();
	}


	
	
}
