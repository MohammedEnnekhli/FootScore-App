package com.score.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable {

    @Id
    private String     role;

    @ManyToMany( mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<User> user;

    public Role() {

    }

    public Role( String role ) {
        super();
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser( List<User> user ) {
        this.user = user;
    }


}
