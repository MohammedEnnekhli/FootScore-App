package com.score.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.score.entities.Club;
import com.score.entities.League;

@Transactional
public interface ClubRepository extends JpaRepository<Club, Long> {

    @Query( "select c from Club c where c.league = ?1 order by c.points desc,(c.goals_for-c.goals_against) desc, c.goals_for desc, c.goals_against asc " )
    public List<Club> findAllOrderByPoints( League league );

    @Query( "select c from Club c where c.league = ?1 order by c.pointsHome desc,(c.goals_forHome-c.goals_againstHome) desc, c.goals_forHome desc, c.goals_againstHome asc" )
    public List<Club> findHomeOrderByPoints( League league );

    @Query( "select c from Club c where c.league = ?1 order by (c.points-c.pointsHome) desc,((c.goals_for-c.goals_forHome)-(c.goals_against-c.goals_againstHome)) desc,(c.goals_for-c.goals_forHome) desc,(c.goals_against-c.goals_againstHome) asc" )
    public List<Club> findAwayOrderByPoints( League league );

    @Query( "select c from Club c where c.league = ?1 order by c.goals_for desc, c.points desc,(c.goals_for-c.goals_against) desc,c.goals_against asc" )
    public List<Club> findAllOrderByGoalsFor( League league );

    @Query( "select c from Club c where c.league = ?1 order by c.goals_against asc, c.points desc,(c.goals_for-c.goals_against) desc,c.goals_for desc" )
    public List<Club> findAllOrderByGoalsAgainst( League league );

}
