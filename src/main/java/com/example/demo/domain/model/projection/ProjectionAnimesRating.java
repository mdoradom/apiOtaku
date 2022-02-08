package com.example.demo.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAnimesRating {

    UUID getAnimeid();
    String getName();

    @JsonIgnoreProperties("ratings")
    Set<ProjectionValoration> getRatings();
}
