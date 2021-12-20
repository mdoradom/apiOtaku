package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.ResponseList;
import com.example.demo.domain.model.projection.ProjectionAnimes;
import com.example.demo.domain.model.projection.ProjectionAuthors;
import com.example.demo.domain.model.projection.ProjectionGenres;
import com.example.demo.repository.AuthorsRepository;
import com.example.demo.repository.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/genres")
public class GenresController {
    @Autowired
    private final GenresRepository genresRepository;

    public GenresController( GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllGenres(){
        return ResponseEntity.ok().body(new ResponseList(genresRepository.findBy(ProjectionGenres.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findGenderByID(@PathVariable UUID id){
        List<ProjectionGenres> genre = genresRepository.findByGenreid(id, ProjectionGenres.class);
        if (genre != null) {
            return  ResponseEntity.ok().body(genre);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat el genre amb id '" + id + "'"));

    }

}