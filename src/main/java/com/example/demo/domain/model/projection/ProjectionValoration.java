package com.example.demo.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

public interface ProjectionValoration {
    UUID getAnimeid();
    UUID getUserid();

    @JsonIgnoreProperties("ratings")
    double getRating();
}
