package com.example.demo.domain.model.projection;

import java.util.List;
import java.util.UUID;

public interface ProjectionUserFavorites {
    UUID getUserid();
    String getUsername();

    List<ProjectionFavAnimes> getFavorites();
}
