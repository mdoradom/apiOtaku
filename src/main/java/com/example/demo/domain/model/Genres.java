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

    public Set<Animes> getAnimes() {
        return animes;
    }

    public void setAnimes( Set<Animes> animes ) {
        this.animes = animes;
    }

    public UUID getGenreid() {
        return genreid;
    }

    public void setGenreid( UUID genreid ) {
        this.genreid = genreid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel( String label ) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }
}
