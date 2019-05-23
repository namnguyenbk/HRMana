package com.springweb.application.repository;

import com.springweb.application.model.ProjectEntity;
import com.springweb.application.model.ProjectMemberEntity;
import com.springweb.application.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepo extends JpaRepository<ProjectMemberEntity, Integer> {
    List<ProjectMemberEntity> findProjectMemberEntityByProject(ProjectEntity projectEntity);
    List<ProjectMemberEntity> findProjectMemberEntitiesByMember(String memberId);
    void deleteAllByProject( ProjectEntity project);
}
