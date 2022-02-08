package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "genres")
public class Genres {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID genreid;

    public String label;
    public String image;

    @ManyToMany(mappedBy = "genres")
    public Set<Animes> animes;
}
