package com.example.demo.domain.model.projection;

import java.util.UUID;

public interface ProjectionMessages {
    UUID getSender();
    UUID getReceiver();
    String getMessage();
}