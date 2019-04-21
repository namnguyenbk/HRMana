package com.springweb.application.model;


import lombok.Data;
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
    private String task_id;

    @Column(name="task_code",columnDefinition = "nvarchar(128)",nullable=false)
    private String taskCode;

    @Column(name="task_name",columnDefinition = "nvarchar(128)",nullable=false)
    private String taskName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn( name = "project_id")
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
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UsersEntity author;

    @OneToOne()
    @JoinColumn(name = "userId",  insertable = false, updatable = false)
    private UsersEntity assigned;

    @Column(name="tag",columnDefinition="nvarchar(64)", nullable=false)
    private String tag;

    @Column(name="due_date",columnDefinition="nvarchar(64)", nullable=false)
    private String dueDate;

    @Column(name="description",columnDefinition="nvarchar(512)", nullable=false)
    private String description;


}
