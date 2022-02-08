package com.example.demo.repository;

import com.example.demo.domain.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FollowersRepository extends JpaRepository<Follower, UUID> {
    Follower findByuserid(UUID id);
}
