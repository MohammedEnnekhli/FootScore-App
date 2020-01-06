package com.score.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Continent implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long         id_continent;
    @NotNull
    @Size( min = 4, max = 25 )
    @Column( unique = true )
    private String       name_continent;

    @OneToMany( mappedBy = "continent", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    /*
     * cascade = CascadeType.ALL: signifie si le continent est supprimé,toutes
     * les ligues qui sont dans ce continent seront aussi supprimées. mais
     * l'inverse non.(si une ligues est supprimée, son continent ne sera pas
     * supprimé)
     */
    private List<League> leagues;

    public Continent() {
        super();
    }

    public Continent( String name_continent, List<League> leagues ) {
        super();
        this.name_continent = name_continent;
        this.leagues = leagues;
    }

    public Continent( @NotNull @Size( max = 25 ) String name_continent ) {
        super();
        this.name_continent = name_continent;
    }

    public Long getId_continent() {
        return id_continent;
    }

    public void setId_continent( Long id_continent ) {
        this.id_continent = id_continent;
    }

    public String getName_continent() {
        return name_continent;
    }

    public void setName_continent( String name_continent ) {
        this.name_continent = name_continent;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues( List<League> leagues ) {
        this.leagues = leagues;
    }

}
