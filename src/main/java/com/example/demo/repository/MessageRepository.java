package com.example.demo.repository;

import com.example.demo.domain.model.Messages;
import com.example.demo.domain.model.projection.ProjectionMessages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Messages, UUID> {
    Optional<Messages> findBymessageid(UUID id);
    void deleteById(UUID id);
    <T> List<T> findBy(Class<T> type);
    List<Messages> findByReceiver(UUID id);
    <T> List<T> findBysender(UUID id, Class<T> type);

}
