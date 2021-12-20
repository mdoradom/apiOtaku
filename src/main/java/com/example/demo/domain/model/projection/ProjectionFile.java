package com.example.demo.domain.model.projection;

import java.util.UUID;

public interface ProjectionFile {
    UUID getFileid();
    String getContenttype();
    String getDescription();
}
