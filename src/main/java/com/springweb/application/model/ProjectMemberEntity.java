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

    @EmbeddedId
    private ProjectMemberId projectMemberId;

    @ManyToOne
    @MapsId("project_id")
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private UsersEntity member;


    @Column(name="mem_role_id",columnDefinition="nvarchar(64)", nullable=false)
    private String memRoleId;
//
    @Column(name="from_date",columnDefinition="nvarchar(64)", nullable=true)
    private String created ;
}
