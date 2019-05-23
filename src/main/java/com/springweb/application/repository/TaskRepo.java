package com.springweb.application.repository;

import com.springweb.application.model.ProjectEntity;
import com.springweb.application.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo    extends JpaRepository<TaskEntity, String> {
    TaskEntity findByTaskId(String id);
    List<TaskEntity> findTaskEntitiesByProject(String project);
}
