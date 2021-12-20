package com.example.demo.domain.model.projection;

import com.example.demo.domain.model.Animes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionUser {
    UUID getUserid();
    String getUsername();
}
