package com.example.demo.repository;

import com.example.demo.domain.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GenresRepository extends JpaRepository<Genres, UUID> {

    <T> List<T> findBy(Class<T> type);

    <T> List<T> findByGenreid(UUID id, Class<T> type);
}