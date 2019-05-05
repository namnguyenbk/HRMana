package com.springweb.application.repository;

import com.springweb.application.model.ProjectEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo  extends JpaRepository<ProjectEntity, String> {
    @Override
    List<ProjectEntity> findAll(Sort sort);

    ProjectEntity findByProjectId( String id);
    List<ProjectEntity> findProjectEntitiesByOwnerIdAndStatusIdAndType(String ownerId, String statusId, String type);
}
