package com.example.demo.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="usser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID userid;
    public String username;

    @JsonIgnore
    public String password;

    @JsonIgnore
    public String role;

    @JsonIgnore
    public boolean enabled;

    @ManyToMany(mappedBy = "favoritedby")
    public Set<Animes> favorites;
}
