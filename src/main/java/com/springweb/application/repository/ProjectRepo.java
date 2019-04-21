package com.springweb.application.repository;

import com.springweb.application.model.ProjectEntity;
import com.springweb.application.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo  extends JpaRepository<UsersEntity, String> {
}
