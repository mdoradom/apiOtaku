package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.ResponseList;
import com.example.demo.domain.model.Animes;
import com.example.demo.domain.model.Result;
import com.example.demo.domain.model.projection.ProjectionAnimes;
import com.example.demo.domain.model.projection.ProjectionAuthors;
import com.example.demo.repository.AnimesRepository;
import org.hibernate.criterion.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/animes")
public class AnimesController {
    @Autowired
    private final AnimesRepository animeRepository;

    public AnimesController(AnimesRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllAnimes(){
        return ResponseEntity.ok().body(new ResponseList(animeRepository.findBy(ProjectionAnimes.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAnimeByID(@PathVariable UUID id){
        List<ProjectionAnimes> anime = animeRepository.findByAnimeid(id, ProjectionAnimes.class);
        if (anime != null) {
            return  ResponseEntity.ok().body(anime);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat l'anime amb id '" + id + "'"));

    }

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody Animes anime) {
        if (animeRepository.findByName(anime.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ja existeix un anime amb el nom '"+anime.name+"'");
        } else {
            Animes animePost = animeRepository.save(anime);
            return ResponseEntity.ok().body(animeRepository.save(anime));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable UUID id) {
        Animes anime = animeRepository.findById(id).orElse(null);
        if (anime != null) {
            animeRepository.delete(anime);
            return  ResponseEntity.ok().body(ErrorMessage.message("S'ha eliminat l'anime amd id " + "'" + id + "'"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat l'anime amb id '" + id + "'"));
    }
}
