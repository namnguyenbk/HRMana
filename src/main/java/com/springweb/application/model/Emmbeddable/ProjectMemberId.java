package com.springweb.application.model.Emmbeddable;

import com.springweb.application.model.ProjectEntity;
import com.springweb.application.model.UsersEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
public class ProjectMemberId implements Serializable {

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "member_id")
    private String memberId;



}
