package com.example.demo.domain.model;

import com.example.demo.domain.model.compositekeys.AnimeUserKey;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "valoration")
@IdClass(AnimeUserKey.class)
public class Valoration {

    @Id
    public
    UUID animeid;

    @Id
    public
    UUID userid;

    public double rating;

    @ManyToOne
    @MapsId("animeid")
    @JoinColumn(name = "animeid")
    public Animes anime;

    @ManyToOne
    @MapsId("userid")
    @JoinColumn(name = "userid")
    public User user;

}