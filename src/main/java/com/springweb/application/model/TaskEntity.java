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

}
