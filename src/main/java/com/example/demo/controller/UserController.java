package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.RequestFavorite;
import com.example.demo.domain.dto.UserRegisterRequest;
import com.example.demo.domain.model.Animes;
import com.example.demo.domain.model.Favorite;
import com.example.demo.domain.model.Result;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.projection.ProjectionUserFavorites;
import com.example.demo.repository.AnimesRepository;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private AnimesRepository animesRepository;
    @Autowired private FavoriteRepository favoriteRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(path = "/register" )
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) {

        if (userRepository.findByUsername(userRegisterRequest.username) == null) {
            User user = new User();
            user.username = userRegisterRequest.username;
            user.password = passwordEncoder.encode(userRegisterRequest.password);
            user.enabled = true;
            return ResponseEntity.ok().body(userRepository.save(user).userid.toString());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.message("Nom d'usuari no disponible"));
    }

    @GetMapping("/")
    public Result getALl(){
        return new Result(userRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.username) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ja existeix un usuari amb el nom '" + user.username + "'");
        } else {
            User userPost = userRepository.save(user);
            return ResponseEntity.ok().body(userRepository.save(user));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return  ResponseEntity.ok().body(ErrorMessage.message("S'ha eliminat l'usuari amd id " + "'" + id + "'"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat l'usuari amd id '" + id + "'"));
    }

    @DeleteMapping("/")
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    // WEB REGISTER FORM (for testing)
    @GetMapping("/register/web")
    public String hack(){
        return "<div style='display:flex;flex-direction:column;width:20em;gap:0.5em'>" +
                "<input name='username' id='username' placeholder='Username'>" +
                "<input id='password' type='password' placeholder='Password'>" +
                "<input type='button' value='Register' onclick='fetch(\"/users/register/\",{method:\"POST\",headers:{\"Content-Type\":\"application/json\"},body:`{\"username\":\"${username.value}\",\"password\":\"${password.value}\"}`})'></div>";
    }

    @GetMapping("/favorites")
    public ResponseEntity<?> getFavorites( Authentication authentication) {
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());

            if (authenticatedUser != null) {
                return ResponseEntity.ok().body(userRepository.findByUsername(authentication.getName(), ProjectionUserFavorites.class));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }

    @PostMapping("/favorites")
    public ResponseEntity<?> addFavorites( @RequestBody RequestFavorite requestFavorite,Authentication authentication) {
        Animes anime = animesRepository.findById(requestFavorite.animeid).orElse(null);
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());
            if (authenticatedUser != null) {
                if (anime != null) {
                    Favorite favorite = new Favorite();
                    favorite.animeid = requestFavorite.animeid;
                    favorite.userid = authenticatedUser.userid;
                    return ResponseEntity.ok().body(favoriteRepository.save(favorite));
                } else {
                    return  ResponseEntity.ok().body(ErrorMessage.message("No s'ha trobat l'anime amb id "  + "'" + requestFavorite.animeid + "'"));
                }

            }

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }

    @DeleteMapping("/favorites")
    public ResponseEntity<?> delFavorite(@RequestBody RequestFavorite requestFavorite, Authentication authentication) {
        Favorite favorite2 = favoriteRepository.findByAnimeid(requestFavorite.animeid);
        if (authentication != null) {

            User authenticatedUser = userRepository.findByUsername(authentication.getName());

            if (authenticatedUser != null) {

                if (favorite2 != null) {
                    Favorite favorite = new Favorite();
                    favorite.animeid = requestFavorite.animeid;
                    favorite.userid = authenticatedUser.userid;
                    favoriteRepository.delete(favorite);
                    return ResponseEntity.ok().body(ErrorMessage.message("S'ha eliminat dels favorits l'anime amd id "  + "'" + requestFavorite.animeid + "'"));
                } else {
                    return  ResponseEntity.ok().body(ErrorMessage.message("No s'ha trobat l'anime amb id "  + "'" + requestFavorite.animeid + "'"));
                }

            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }
}