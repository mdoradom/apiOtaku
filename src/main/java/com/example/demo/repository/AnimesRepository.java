package com.example.demo.repository;

import com.example.demo.domain.model.Animes;
import com.example.demo.domain.model.projection.ProjectionAnimes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnimesRepository extends JpaRepository<Animes, UUID> {

    Animes findByName(String name);

    void deleteById(UUID id);

    <T> List<T> findBy(Class<T> type);

    <T> List<T> findByAnimeid(UUID id, Class<T> type);
}
