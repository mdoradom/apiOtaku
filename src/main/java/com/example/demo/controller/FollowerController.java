package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.model.Favorite;
import com.example.demo.domain.model.Follower;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.projection.ProjectionUserFavorites;
import com.example.demo.repository.FollowersRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/followers")
public class FollowerController {
    @Autowired private UserRepository userRepository;
    @Autowired private FollowersRepository followersRepository;

    @GetMapping("/")
    public ResponseEntity<?> getFollowers(Authentication authentication){
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());

            if (authenticatedUser != null) {
                return ResponseEntity.ok().body(followersRepository.findByuserid(authenticatedUser.userid));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getFollowerID(@PathVariable UUID id, Authentication authentication){
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());

            if (authenticatedUser != null) {
                return ResponseEntity.ok().body(followersRepository.findByuserid(id));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> addFollower(@PathVariable UUID id, Authentication authentication){
        User user = userRepository.findById(id).orElse(null);
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());
            if (authenticatedUser != null) {
                if (user != null) {
                    Follower follower = new Follower();
                    follower.followed = user.userid;
                    follower.userid = authenticatedUser.userid;
                    return ResponseEntity.ok().body(followersRepository.save(follower));
                } else {
                    return  ResponseEntity.ok().body(ErrorMessage.message("No s'ha trobat l'usuari amb id "  + "'" + id + "'"));
                }

            }

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFollower(@PathVariable UUID id, Authentication authentication){
        User user = userRepository.findById(id).orElse(null);
        if (authentication != null) {

            User authenticatedUser = userRepository.findByUsername(authentication.getName());

            if (authenticatedUser != null) {

                if (user != null) {
                    Follower follower = new Follower();
                    follower.followed = id;
                    follower.userid = authenticatedUser.userid;
                    followersRepository.delete(follower);
                    return ResponseEntity.ok().body(ErrorMessage.message("Has deixat de seguir a l'usuari amb id "  + "'" + id + "'"));
                } else {
                    return  ResponseEntity.ok().body(ErrorMessage.message("No s'ha trobat el follower amb id "  + "'" + id + "'"));
                }

            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));

    }

}
