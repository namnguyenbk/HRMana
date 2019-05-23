package com.springweb.application.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="project")
public class ProjectEntity {

    @Id
    @GenericGenerator(name="generator",strategy="guid")
    @GeneratedValue(generator="generator")
    @Column(name="project_id")
    private String projectId;

    @Column(name="ownerId",columnDefinition="nvarchar(64)", nullable=false)
    private String ownerId;

    @Column(name="project_name",columnDefinition = "nvarchar(128)",nullable=false)
    private String projectName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private  Set<TaskEntity> listTasks ;

    @Column(name="type",columnDefinition="nvarchar(64)", nullable=false)
    private String type;

    @Column(name="created",columnDefinition="nvarchar(64)", nullable=true)
    private String created ;

    @Column(name="description",columnDefinition="nvarchar(512)", nullable=true)
    private String description;

    @Column(name="status_id",columnDefinition="nvarchar(64)", nullable=false)
    private String statusId;

    @Column(name="theme",columnDefinition="nvarchar(64)", nullable=true)
    private String theme;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Set<TaskEntity> getListTasks() {
		return listTasks;
	}

	public void setListTasks(Set<TaskEntity> listTasks) {
		this.listTasks = listTasks;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
    

}
