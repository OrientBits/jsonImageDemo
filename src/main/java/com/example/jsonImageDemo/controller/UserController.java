package com.example.jsonImageDemo.controller;

import com.example.jsonImageDemo.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;

    Logger logger =  LoggerFactory.getLogger(UserController.class);


    @PostMapping
    public ResponseEntity<?> saveUser(@RequestParam("userData") String userData, @RequestParam("file") MultipartFile file){


        System.out.println("File name: "+file.getOriginalFilename());

        User user = null;
        try {
            user = objectMapper.readValue(userData, User.class);
            user.setImageName(file.getOriginalFilename());
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }


        logger.info("User: "+user);

        return ResponseEntity.ok(user);
    }


}
