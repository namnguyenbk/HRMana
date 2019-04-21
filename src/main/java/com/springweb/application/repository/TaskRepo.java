package com.springweb.application.repository;

import com.springweb.application.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo  extends JpaRepository<UsersEntity, String> {

}
