package com.example.demo.repository;

import com.example.demo.domain.model.Animes;
import com.example.demo.domain.model.Valoration;
import com.example.demo.domain.model.projection.ProjectionValoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ValorationRepository extends JpaRepository<Valoration, UUID> {

}
