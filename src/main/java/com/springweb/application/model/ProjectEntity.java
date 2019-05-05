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

}
