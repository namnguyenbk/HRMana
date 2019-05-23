package com.springweb.application.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @Id
    @Column(name="member_id",columnDefinition="nvarchar(64)", nullable=false)
//    @JoinColumn(name = "member_id", referencedColumnName = "userID")
    private String member;

    @Column(name="mem_role_id",columnDefinition="nvarchar(64)", nullable=false)
    private String memRoleId;
//
    @Column(name="from_date",columnDefinition="nvarchar(64)", nullable=true)
    private String created ;
}
