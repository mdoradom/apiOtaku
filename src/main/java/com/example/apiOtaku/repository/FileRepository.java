package com.example.apiOtaku.repository;

import com.example.apiOtaku.domain.model.File;
import com.example.apiOtaku.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
    @Query("select fileid from File")
    List<String> getFileIds();
}
