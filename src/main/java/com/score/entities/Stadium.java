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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Stadium implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long         id_stadium;
    @NotNull
    @Size( max = 50 )
    @Column( unique = true )
    private String       name_stadium;
    @NotNull
    @Size( max = 50 )
    private String       location;
    @NotNull
    @Size( max = 50 )
    @Column( unique = true )
    private String       owner;
    @NotNull
    private int          capacity;
    @NotNull
    @Temporal( TemporalType.DATE )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date         built_stadium;

    @OneToMany( mappedBy = "stadium", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Matche> matchs;

    @OneToMany( mappedBy = "stadium", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Club>   clubs;

    public Stadium() {
        super();
    }

    public Stadium( String name_stadium, String location, String owner, int capacity, Date built_stadium ) {
        super();
        this.name_stadium = name_stadium;
        this.location = location;
        this.owner = owner;
        this.capacity = capacity;
        this.built_stadium = built_stadium;
    }

    public Long getId_stadium() {
        return id_stadium;
    }

    public void setId_stadium( Long id_stadium ) {
        this.id_stadium = id_stadium;
    }

    public String getName_stadium() {
        return name_stadium;
    }

    public void setName_stadium( String name_stadium ) {
        this.name_stadium = name_stadium;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation( String location ) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner( String owner ) {
        this.owner = owner;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity( int capacity ) {
        this.capacity = capacity;
    }

    public Date getBuilt_stadium() {
        return built_stadium;
    }

    public void setBuilt_stadium( Date built_stadium ) {
        this.built_stadium = built_stadium;
    }

    public List<Matche> getMatchs() {
        return matchs;
    }

    public void setMatchs( List<Matche> matchs ) {
        this.matchs = matchs;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs( List<Club> clubs ) {
        this.clubs = clubs;
    }

}
