package com.springweb.application.repository;

import com.springweb.application.model.ProjectMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepo extends JpaRepository<ProjectMemberEntity, Integer> {

}
