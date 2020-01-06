package com.score.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.score.entities.League;

@Transactional
public interface LeagueRepository extends JpaRepository<League, Long> {

}
