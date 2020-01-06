package com.score.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Matche implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long       id_match;
    @Temporal( TemporalType.DATE )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @NotNull
    private Date       date_match;

    @ManyToOne( optional = false )
    @JoinColumn( name = "league_id" )
    private League     league;

    @ManyToOne( optional = false )
    @JoinColumn( name = "stadium_id" )
    private Stadium    stadium;

    @ManyToMany( mappedBy = "matchs", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Club> clubs;

    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    @JoinColumn( name = "home_team_id" )
    private Club       home_team;
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    @JoinColumn( name = "away_team_id" )
    private Club       away_team;

    private int        goals_home_team;
    private int        goals_away_team;
    private int        round;

    public Matche() {
        super();
    }

    public Matche( @NotNull Date date_match, League league, Club home_team, Club away_team,
            int goals_home_team, int goals_away_team, int round ) {
        super();
        this.date_match = date_match;
        this.league = league;
        this.home_team = home_team;
        this.away_team = away_team;
        this.goals_home_team = goals_home_team;
        this.goals_away_team = goals_away_team;
        this.round = round;
    }

    public Matche( @NotNull Date date_match, Stadium stadium, League league, Club home_team, Club away_team,
            int round ) {
        super();
        this.date_match = date_match;
        this.league = league;
        this.home_team = home_team;
        this.away_team = away_team;
        this.round = round;
        this.stadium = stadium;
    }

    public Long getId_match() {
        return id_match;
    }

    public void setId_match( Long id_match ) {
        this.id_match = id_match;
    }

    public Date getDate_match() {
        return date_match;
    }

    public void setDate_match( Date date_match ) {
        this.date_match = date_match;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague( League league ) {
        this.league = league;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium( Stadium stadium ) {
        this.stadium = stadium;
    }

    public Club getHome_team() {
        return home_team;
    }

    public void setHome_team( Club home_team ) {
        this.home_team = home_team;
    }

    public Club getAway_team() {
        return away_team;
    }

    public void setAway_team( Club away_team ) {
        this.away_team = away_team;
    }

    public int getGoals_home_team() {
        return goals_home_team;
    }

    public void setGoals_home_team( int goals_home_team ) {
        this.goals_home_team = goals_home_team;
    }

    public int getGoals_away_team() {
        return goals_away_team;
    }

    public void setGoals_away_team( int goals_away_team ) {
        this.goals_away_team = goals_away_team;
    }

    public int getRound() {
        return round;
    }

    public void setRound( int round ) {
        this.round = round;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs( List<Club> clubs ) {
        this.clubs = clubs;
    }

}
