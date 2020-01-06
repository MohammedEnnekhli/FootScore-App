package com.score.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.score.entities.Continent;

@Transactional
public interface ContinentRepository extends JpaRepository<Continent, Long> {

}
