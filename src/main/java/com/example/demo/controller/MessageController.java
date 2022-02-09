package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.RequestMessage;
import com.example.demo.domain.model.Messages;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.projection.ProjectionMessages;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/msg")
public class MessageController {
    @Autowired private MessageRepository messageRepository;
    @Autowired private UserRepository userRepository;
    @PostMapping("/")
    public ResponseEntity<?> sendMSG(@RequestBody RequestMessage requestMessage, Authentication authentication){
        User authenticatedUser = userRepository.findByUsername(authentication.getName());
        if (authenticatedUser != null) {
            Messages msg = new Messages();
            msg.sender = authenticatedUser.userid;
            msg.receiver = requestMessage.receiver;
            msg.message = requestMessage.message;
            messageRepository.save(msg);
            return ResponseEntity.ok().body(ErrorMessage.message("S'ha enviat el missatge."));
        }else{
            return  ResponseEntity.ok().body(ErrorMessage.message("No s'ha enviat el missatge."));
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getMSG(Authentication authentication){
        User authenticatedUser = userRepository.findByUsername(authentication.getName());
        if (authenticatedUser != null) {
            List<Messages> pj = messageRepository.findByReceiver(authenticatedUser.userid);
            return ResponseEntity.ok().body(pj);
        }else{
            return  ResponseEntity.ok().body(ErrorMessage.message("No amigo ahí no es."));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMSG(@PathVariable UUID id, Authentication authentication){
        User authenticatedUser = userRepository.findByUsername(authentication.getName());
        if (authenticatedUser != null) {
            Messages messages = messageRepository.findBymessageid(id).orElse(null);
            if(messages!=null) {
                for (Messages msg : messageRepository.findAll()) {
                    if (msg.messageid.equals(id)) {
                        messageRepository.delete(msg);
                    }
                }
                return ResponseEntity.ok().body(ErrorMessage.message("S'ha eliminat el missatge"));
            }else{
                return ResponseEntity.ok().body(ErrorMessage.message("No s'ha eliminat el missatge"));
            }
        }else{
            return  ResponseEntity.ok().body(ErrorMessage.message("Unauthorized."));
        }
    }
}
