package com.example.demo.domain.model.compositekeys;

import java.io.Serializable;
import java.util.UUID;

public class FollowUserKey implements Serializable {
    public UUID userid;
    public UUID followed;
}
