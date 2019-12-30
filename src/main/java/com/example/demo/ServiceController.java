package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ServiceController {

    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User register(@RequestBody Map<String,String> body){

        String email = body.get("email");
        String username = body.get("username");
        String password = body.get("password");

        User user = new User(email,username,password);
        return userRepository.save(user);

    }
}