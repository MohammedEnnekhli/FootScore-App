package com.score.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.score.entities.Role;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

}
