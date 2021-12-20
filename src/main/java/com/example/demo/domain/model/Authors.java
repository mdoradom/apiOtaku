package com.example.demo.domain.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID authorid;

    public String name;
    public String image;

    @ManyToMany(mappedBy = "authors")
    public Set<Animes> animes;

    public Set<Animes> getAnimes() {
        return animes;
    }

    public void setAnimes( Set<Animes> animes ) {
        this.animes = animes;
    }

    public UUID getAuthorid() {
        return authorid;
    }

    public void setAuthorid( UUID authorid ) {
        this.authorid = authorid;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }
}
