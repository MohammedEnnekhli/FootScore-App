package com.score.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Athletic implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long   id_athletic;
    @NotNull
    @Size( max = 50 )
    @Column( unique = true )
    private String name_athletic;
    @NotNull
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @Temporal( TemporalType.DATE )
    private Date   date_birth;
    @NotNull
    @Size( max = 25 )
    private String nationality;
    @NotNull
    @Size( max = 25 )
    private String position;
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    @JoinColumn( name = "club_id" )
    private Club   club;

    public Athletic() {
        super();
    }

    public Athletic( String name_athletic, Date date_birth, String nationality, String position, Club club ) {
        super();
        this.name_athletic = name_athletic;
        this.date_birth = date_birth;
        this.nationality = nationality;
        this.position = position;
        this.club = club;
    }

    public Long getId_athletic() {
        return id_athletic;
    }

    public void setId_athletic( Long id_athletic ) {
        this.id_athletic = id_athletic;
    }

    public String getName_athletic() {
        return name_athletic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition( String position ) {
        this.position = position;
    }

    public void setName_athletic( String name_athletic ) {
        this.name_athletic = name_athletic;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth( Date date_birth ) {
        this.date_birth = date_birth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality( String nationality ) {
        this.nationality = nationality;
    }

    public Club getClub() {
        return club;
    }

    public void setClub( Club club ) {
        this.club = club;
    }

}
