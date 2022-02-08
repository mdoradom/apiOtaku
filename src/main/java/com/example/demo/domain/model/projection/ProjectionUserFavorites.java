package com.example.demo.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.UUID;

public interface ProjectionUserFavorites {
    UUID getUserid();
    String getUsername();

    @JsonIgnoreProperties("favorites")
    List<ProjectionFavAnimes> getFavorites();
}
