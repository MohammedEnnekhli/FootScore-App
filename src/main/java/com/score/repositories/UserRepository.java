package com.score.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.score.entities.User;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

}
