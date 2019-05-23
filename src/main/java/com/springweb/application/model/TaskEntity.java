package com.springweb.application.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="task")
public class TaskEntity {

    @Id
    @GenericGenerator(name="generator",strategy="guid")
    @GeneratedValue(generator="generator")
    @Column(name="task_id")
    private String taskId;

    @Column(name="task_code",columnDefinition = "nvarchar(128)",nullable=false)
    private String taskCode;

    @Column(name="task_name",columnDefinition = "nvarchar(128)",nullable=false)
    private String taskName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn( name = "project_id", referencedColumnName = "project_id")
    private ProjectEntity project ;

    @Column(name="status_id",columnDefinition="nvarchar(64)", nullable=false)
    private String statusId;

    @Column(name="activity_id",columnDefinition="nvarchar(64)", nullable=true)
    private String activityId ;

    @Column(name="comment_id",columnDefinition="nvarchar(64)", nullable=true)
    private String commentId;

    @Column(name="created",columnDefinition="nvarchar(64)", nullable=false)
    private String created;

    @OneToOne()
    @JoinColumn(name = "authorId", referencedColumnName = "userID")
    private UsersEntity author;

    @OneToOne()
    @JoinColumn(name = "assigned", referencedColumnName = "userID")
    private UsersEntity assigned;

    @Column(name="tag",columnDefinition="nvarchar(64)", nullable=false)
    private String tag;

    @Column(name="due_date",columnDefinition="nvarchar(64)", nullable=false)
    private String dueDate;

    @Column(name="description",columnDefinition="nvarchar(512)", nullable=false)
    private String description;

    public TaskEntity(String taskCode, String taskName, ProjectEntity project,
                      String statusId, String activityId, String commentId,
                      String created, UsersEntity author, UsersEntity assigned,
                      String tag, String dueDate, String description) {
        this.taskCode = taskCode;
        this.taskName = taskName;
        this.project = project;
        this.statusId = statusId;
        this.activityId = activityId;
        this.commentId = commentId;
        this.created = created;
        this.author = author;
        this.assigned = assigned;
        this.tag = tag;
        this.dueDate = dueDate;
        this.description = description;
    }

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public UsersEntity getAuthor() {
		return author;
	}

	public void setAuthor(UsersEntity author) {
		this.author = author;
	}

	public UsersEntity getAssigned() {
		return assigned;
	}

	public void setAssigned(UsersEntity assigned) {
		this.assigned = assigned;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    

}
