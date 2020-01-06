package com.score.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.score.entities.League;
import com.score.entities.Matche;

@Transactional
public interface MatcheRepository extends JpaRepository<Matche, Long> {

    @Query( "select m from Matche m where m.league=?1 order by m.round desc,m.date_match desc" )
    public List<Matche> findByLeagueOrderByRoundDesc( League league );

}
