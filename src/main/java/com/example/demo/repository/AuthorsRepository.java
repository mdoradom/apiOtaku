package com.example.demo.repository;

import com.example.demo.domain.model.Animes;
import com.example.demo.domain.model.Authors;
import com.example.demo.domain.model.projection.ProjectionAnimes;
import com.example.demo.domain.model.projection.ProjectionAuthors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorsRepository extends JpaRepository<Authors, UUID> {

    <T> List<T> findBy(Class<T> type);

    <T> List<T> findByAuthorid(UUID id, Class<T> type);
}