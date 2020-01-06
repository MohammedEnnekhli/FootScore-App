package com.score.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Club implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long           id_club;
    @NotNull
    @Size( max = 60 )
    @Column( unique = true )
    private String         name_club;
    private int            nbr_champ;
    @Temporal( TemporalType.DATE )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @NotNull
    private Date           date_creation;
    @NotNull
    @Size( max = 25 )
    private String         city;

    private String         clubLogo;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "league_id" )
    private League         league;

    @OneToMany( mappedBy = "club", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Athletic> athletics;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "stadium_id" )
    private Stadium        stadium;

    private int            played;
    private int            playedHome;
    private int            won;
    private int            wonHome;
    private int            drawn;
    private int            drawnHome;
    private int            goals_for;
    private int            goals_forHome;
    private int            goals_against;
    private int            goals_againstHome;
    private int            points;
    private int            pointsHome;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable( name = "Club_Match", joinColumns = { @JoinColumn( name = "club_id" ) }, inverseJoinColumns = {
            @JoinColumn( name = "match_id" ) } )
    private List<Matche>   matchs;

    @OneToMany( mappedBy = "home_team", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Matche>   home_matchs;
    @OneToMany( mappedBy = "away_team", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Matche>   away_matchs;

    public Club() {
        super();
    }

    public Club( String name_club, int nbr_champ, Date date_creation, String city, League league, Stadium stadium,
            String clubLogo ) {
        super();
        this.name_club = name_club;
        this.nbr_champ = nbr_champ;
        this.date_creation = date_creation;
        this.city = city;
        this.league = league;
        this.stadium = stadium;
        this.clubLogo = clubLogo;
    }

    public Character resultMatche( Matche matche ) {
        int goalsHome = matche.getGoals_home_team();
        int goalsAway = matche.getGoals_away_team();
        if ( matche.getHome_team().id_club == this.id_club ) {
            if ( goalsHome > goalsAway ) {
                return 'W';
            } else if ( goalsHome < goalsAway ) {
                return 'L';
            } else {
                return 'D';
            }

        } else if ( matche.getAway_team().id_club == this.id_club ) {
            if ( goalsHome < goalsAway ) {
                return 'W';
            } else if ( goalsHome > goalsAway ) {
                return 'L';
            } else {
                return 'D';
            }

        } else
            return null;

    }

    public Long getId_club() {
        return id_club;
    }

    public void setId_club( Long id_club ) {
        this.id_club = id_club;
    }

    public String getName_club() {
        return name_club;
    }

    public void setName_club( String name_club ) {
        this.name_club = name_club;
    }

    public int getNbr_champ() {
        return nbr_champ;
    }

    public void setNbr_champ( int nbr_champ ) {
        this.nbr_champ = nbr_champ;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation( Date date_creation ) {
        this.date_creation = date_creation;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague( League league ) {
        this.league = league;
    }

    public List<Athletic> getAthletics() {
        return athletics;
    }

    public void setAthletics( List<Athletic> athletics ) {
        this.athletics = athletics;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium( Stadium stadium ) {
        this.stadium = stadium;
    }

    public List<Matche> getHome_matchs() {
        return home_matchs;
    }

    public void setHome_matchs( List<Matche> home_matchs ) {
        this.home_matchs = home_matchs;
    }

    public List<Matche> getAway_matchs() {
        return away_matchs;
    }

    public void setAway_matchs( List<Matche> away_matchs ) {
        this.away_matchs = away_matchs;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed( int played ) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon( int won ) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn( int drawn ) {
        this.drawn = drawn;
    }

    public int getGoals_for() {
        return goals_for;
    }

    public void setGoals_for( int goals_for ) {
        this.goals_for = goals_for;
    }

    public int getGoals_against() {
        return goals_against;
    }

    public void setGoals_against( int goals_against ) {
        this.goals_against = goals_against;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints( int points ) {
        this.points = points;
    }

    public int getWonHome() {
        return wonHome;
    }

    public void setWonHome( int wonHome ) {
        this.wonHome = wonHome;
    }

    public int getDrawnHome() {
        return drawnHome;
    }

    public void setDrawnHome( int drawnHome ) {
        this.drawnHome = drawnHome;
    }

    public int getGoals_forHome() {
        return goals_forHome;
    }

    public void setGoals_forHome( int goals_forHome ) {
        this.goals_forHome = goals_forHome;
    }

    public int getGoals_againstHome() {
        return goals_againstHome;
    }

    public void setGoals_againstHome( int goals_againstHome ) {
        this.goals_againstHome = goals_againstHome;
    }

    public int getPointsHome() {
        return pointsHome;
    }

    public void setPointsHome( int pointsHome ) {
        this.pointsHome = pointsHome;
    }

    public int getPlayedHome() {
        return playedHome;
    }

    public void setPlayedHome( int playedHome ) {
        this.playedHome = playedHome;
    }

    public String getClubLogo() {
        return clubLogo;
    }

    public void setClubLogo( String clubLogo ) {
        this.clubLogo = clubLogo;
    }

    public List<Matche> getMatchs() {
        return matchs;
    }

    public void setMatchs( List<Matche> matchs ) {
        this.matchs = matchs;
    }

}
