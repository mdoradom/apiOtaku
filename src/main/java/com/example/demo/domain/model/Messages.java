package com.example.demo.domain.model;

import com.example.demo.domain.model.compositekeys.AnimeUserKey;
import com.example.demo.domain.model.compositekeys.SenderReceiverKey;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "messages")
@IdClass(SenderReceiverKey.class)
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID messageid;
    @Id
    public UUID sender;
    @Id
    public UUID receiver;
    public String message;

}
