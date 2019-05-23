package com.springweb.application.model;


import com.springweb.application.model.Emmbeddable.ProjectMemberId;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="project_member")
public class ProjectMemberEntity {

    @Id
    @GenericGenerator(name="generator",strategy="guid")
    @GeneratedValue(generator="generator")
    @Column(name="project_member_id")
    private String projectMemberId;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private ProjectEntity project;

    @ManyToOne()
    @JoinColumn(name = "member_id", referencedColumnName = "userID")
    private UsersEntity member;

    @Column(name="mem_role_id",columnDefinition="nvarchar(64)", nullable=false)
    private String memRoleId;
//
    @Column(name="from_date",columnDefinition="nvarchar(64)", nullable=true)
    private String created ;
	public String getProjectMemberId() {
		return projectMemberId;
	}
	public void setProjectMemberId(String projectMemberId) {
		this.projectMemberId = projectMemberId;
	}
	public ProjectEntity getProject() {
		return project;
	}
	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	public UsersEntity getMember() {
		return member;
	}
	public void setMember(UsersEntity member) {
		this.member = member;
	}
	public String getMemRoleId() {
		return memRoleId;
	}
	public void setMemRoleId(String memRoleId) {
		this.memRoleId = memRoleId;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
    
}
