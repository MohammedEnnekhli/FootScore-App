package com.score.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity

public class User implements Serializable {
    @Id
    private String     login;
    private String     password;
    private int        active;
    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable( name = "authorities", joinColumns = { @JoinColumn( name = "login" ) }, inverseJoinColumns = {
            @JoinColumn( name = "role" ) } )
    private List<Role> role;

    public User() {

    }

    public User( String login, String password, int active ) {
        super();
        this.login = login;
        this.password = password;
        this.active = active;
    }

    public User( String login, String password, int active, List<Role> role ) {
        super();
        this.login = login;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive( int active ) {
        this.active = active;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole( List<Role> role ) {
        this.role = role;
    }

}
