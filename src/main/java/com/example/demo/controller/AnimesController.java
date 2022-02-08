package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.ResponseList;
import com.example.demo.domain.model.Animes;
import com.example.demo.domain.model.Valoration;
import com.example.demo.domain.model.projection.ProjectionAnimesRating;
import com.example.demo.domain.model.projection.ProjectionAnimes;
import com.example.demo.repository.AnimesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ValorationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/animes")
public class AnimesController {
    @Autowired private final AnimesRepository animeRepository;

    @Autowired private UserRepository userRepository;
    @Autowired private ValorationRepository valorationRepository;

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
        if (animeRepository.findByName(anime.name) != null) {
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

    @GetMapping("/rating/{id}")
    public ResponseEntity<?> getRating(@PathVariable UUID id){
        List<ProjectionAnimesRating> anime = animeRepository.findByAnimeid(id, ProjectionAnimesRating.class);
        if (anime != null) {
            return  ResponseEntity.ok().body(anime);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat l'anime amb id '" + id + "'"));
    }


    @PostMapping("/rating")
    public ResponseEntity<?> createRating(@RequestBody Valoration valoration) {
        Valoration valorationPost = new Valoration();
        valorationPost.userid = valoration.userid;
        valorationPost.animeid = valoration.animeid;
        valorationPost.rating = valoration.rating;
        valorationRepository.save(valorationPost);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/rating/{animeid}/{userid}")
    public ResponseEntity<?> deleteRating(@PathVariable UUID animeid, @PathVariable UUID userid) {
        List <Valoration> valorations = valorationRepository.findAll();
        for (Valoration val : valorations) {
            if (val.animeid.equals(animeid) && val.userid.equals(userid)) {
                valorationRepository.delete(val);
                return ResponseEntity.ok().body("registro eliminado");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat la valoració"));
    }
}