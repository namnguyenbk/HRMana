package com.springweb.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimbusds.oauth2.sdk.Role;
import com.springweb.application.model.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{
	RoleEntity findByName(String name);
}
