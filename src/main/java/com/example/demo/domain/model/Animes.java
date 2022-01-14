package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "animes")
public class Animes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID animeid;

    public String name;
    public String description;
    public String type;
    public int yearr;
    public String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JsonIgnoreProperties("animes")
    @JoinTable(name = "anime_author", joinColumns = @JoinColumn(name ="animeid"), inverseJoinColumns = @JoinColumn(name = "authorid"))
    public Set<Authors> authors;

    @ManyToMany
    @JsonIgnoreProperties("animes")
    @JoinTable(name = "anime_genre", joinColumns = @JoinColumn(name ="animeid"), inverseJoinColumns = @JoinColumn(name = "genreid"))
    public Set<Genres> genres;

    @ManyToMany
    @JoinTable(name = "favorite", joinColumns = @JoinColumn(name ="animeid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    public Set<User> favoritedby;
}
