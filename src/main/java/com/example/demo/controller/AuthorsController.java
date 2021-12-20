package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.ResponseList;
import com.example.demo.domain.model.projection.ProjectionAnimes;
import com.example.demo.domain.model.projection.ProjectionAuthors;
import com.example.demo.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    private final AuthorsRepository authorsRepository;

    public AuthorsController( AuthorsRepository authorRepository) {
        this.authorsRepository = authorRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllAuthors(){
        return ResponseEntity.ok().body(new ResponseList(authorsRepository.findBy(ProjectionAuthors.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAuthorByID(@PathVariable UUID id){
        List<ProjectionAuthors> author = authorsRepository.findByAuthorid(id, ProjectionAuthors.class);
        if (author != null) {
            return  ResponseEntity.ok().body(author);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat l'autor amb id '" + id + "'"));

    }

}