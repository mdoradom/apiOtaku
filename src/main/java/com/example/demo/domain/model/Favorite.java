package com.example.demo.domain.model;

import com.example.demo.domain.model.compositekeys.AnimeUserKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "favorite")
@IdClass(AnimeUserKey.class)
public class Favorite {
    @Id
    public UUID userid;
    @Id
    public UUID animeid;
}
