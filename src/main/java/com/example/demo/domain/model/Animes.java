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

    public UUID getAnimeid() {
        return animeid;
    }

    public void setAnimeid(UUID animeid) {
        this.animeid = animeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearr() {
        return yearr;
    }

    public void setYearr(int yearr) {
        this.yearr = yearr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
