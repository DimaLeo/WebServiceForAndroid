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
        User userToSend = new User(9999,email,username,password);
        userRepository.save(user);
        return userToSend;

    }

    @PostMapping(value = "/users/getUsername",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsername(@RequestBody Map<String,String> body){
        String username;
        String emailReceived = body.get("email");
        User user = userRepository.findUserByEmail(emailReceived);
        username= user.getUsername();

        return username;
    }

    @PostMapping(value = "/users/checkPassword",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean checkPassword(@RequestBody Map<String,String> body){
        boolean passwordCorrect = false;
        String email = body.get("email");
        String receivedPassword = body.get("password");

        User user = userRepository.findUserByEmail(email);

        if(user.getPassword().equals(receivedPassword)){

            passwordCorrect = true;
        }


        return passwordCorrect;
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

        String username = body.get("username");
        return userRepository.findAllByUsername(username).size();


    }


}