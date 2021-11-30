package com.example.apiOtaku.controller;

import com.example.apiOtaku.domain.model.Actor;
import com.example.apiOtaku.repository.ActorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorRepository actorRepository;
    ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping("/")
    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }
}
