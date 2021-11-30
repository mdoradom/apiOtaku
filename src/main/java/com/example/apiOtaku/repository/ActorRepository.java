package com.example.apiOtaku.repository;

import com.example.apiOtaku.domain.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor, UUID> {

}
