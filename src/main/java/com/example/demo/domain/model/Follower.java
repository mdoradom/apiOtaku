package com.example.demo.domain.model;



import com.example.demo.domain.model.compositekeys.AnimeUserKey;
import com.example.demo.domain.model.compositekeys.FollowUserKey;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="followers")
@IdClass(FollowUserKey.class)

public class Follower {
    @Id
    public UUID userid;
    @Id
    public UUID followed;
}
