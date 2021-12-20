package com.example.demo.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"animeid","name","description","type","yearr","image","authors","genres"})
public interface ProjectionAnimes {

    UUID getAnimeid();
    String getName();
    String getDescription();
    String getType();
    int getYearr();
    String getImage();

    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthors> getAuthors();

    @JsonIgnoreProperties("genres")
    Set<ProjectionGenres> getGenres();
}
