package com.example.apiOtaku.repository;

import com.example.apiOtaku.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

}
//