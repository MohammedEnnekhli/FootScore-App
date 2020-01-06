package com.score.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.score.entities.Matche;

@Service
@Transactional
public class FootServiceImpl implements FootService {

    @Override
    public void goalsClub( Matche matche ) {

        int goalsHome = matche.getGoals_home_team();
        int goalsAway = matche.getGoals_away_team();
        matche.getHome_team().setGoals_for( matche.getHome_team().getGoals_for() + goalsHome );
        matche.getHome_team().setGoals_forHome( matche.getHome_team().getGoals_forHome() + goalsHome );
        matche.getAway_team().setGoals_for( matche.getAway_team().getGoals_for() + goalsAway );
        matche.getHome_team().setGoals_against( matche.getHome_team().getGoals_against() + goalsAway );
        matche.getHome_team().setGoals_againstHome( matche.getHome_team().getGoals_againstHome() + goalsAway );
        matche.getAway_team().setGoals_against( matche.getAway_team().getGoals_against() + goalsHome );
        matche.getHome_team().setPlayed( matche.getHome_team().getPlayed() + 1 );
        matche.getHome_team().setPlayedHome( matche.getHome_team().getPlayedHome() + 1 );
        matche.getAway_team().setPlayed( matche.getAway_team().getPlayed() + 1 );
        if ( goalsHome > goalsAway ) {
            matche.getHome_team().setWon( matche.getHome_team().getWon() + 1 );
            matche.getHome_team().setWonHome( matche.getHome_team().getWonHome() + 1 );
            matche.getHome_team().setPoints( matche.getHome_team().getPoints() + 3 );
            matche.getHome_team().setPointsHome( matche.getHome_team().getPointsHome() + 3 );
        } else if ( goalsHome < goalsAway ) {
            matche.getAway_team().setWon( matche.getAway_team().getWon() + 1 );
            matche.getAway_team().setPoints( matche.getAway_team().getPoints() + 3 );
        } else {
            matche.getHome_team().setDrawn( matche.getHome_team().getDrawn() + 1 );
            matche.getHome_team().setDrawnHome( matche.getHome_team().getDrawnHome() + 1 );
            matche.getHome_team().setPoints( matche.getHome_team().getPoints() + 1 );
            matche.getHome_team().setPointsHome( matche.getHome_team().getPointsHome() + 1 );
            matche.getAway_team().setDrawn( matche.getAway_team().getDrawn() + 1 );
            matche.getAway_team().setPoints( matche.getAway_team().getPoints() + 1 );

        }

    }

}
