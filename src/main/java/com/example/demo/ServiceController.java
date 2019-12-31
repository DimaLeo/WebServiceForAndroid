package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
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


    @PostMapping(value = "users/findEmail",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int findByEmail(@RequestBody Map<String,String> body){

        String email = body.get("email");
        return userRepository.findAllByEmail(email).size();
    }

    @PostMapping(value = "users/findUsername",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int findByUsername(@RequestBody Map<String,String> body){

        String usernmae = body.get("username");
        return userRepository.findAllByUsername(usernmae).size();


    }


}