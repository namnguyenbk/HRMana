package com.springweb.application.repository;

import com.springweb.application.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo    extends JpaRepository<TaskEntity, String> {

}
