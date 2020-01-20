package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ServiceController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeviceRepository deviceRepository;

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

    @PostMapping(value = "/devices",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Device insertDevice(@RequestBody Map<String,String> body){

        Device device;

        String device_name = body.get("device_name");
        String device_type = body.get("device_type");
        Integer device_brand = Integer.parseInt(body.get("device_brand"));
        Integer device_wattage = Integer.parseInt(body.get("device_wattage"));
        String username = body.get("username");

        device = new Device(device_name,device_type,device_brand,device_wattage,username);

        deviceRepository.save(device);

        return device;
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

    @PostMapping(value = "/users/findEmail",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int findByEmail(@RequestBody Map<String,String> body){

        String email = body.get("email");
        return userRepository.findAllByEmail(email).size();
    }

    @PostMapping(value = "/findDevices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Device> getAllDevicesByUsername(@RequestBody Map<String,String> body){

        String username = body.get("username");

        return deviceRepository.findAllByUsername(username);

    }
    
    @PostMapping(value = "/users/findUsername",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int findByUsername(@RequestBody Map<String,String> body){

        String username = body.get("username");
        return userRepository.findAllByUsername(username).size();


    }

    @PostMapping(value="/users/getHomeConfig",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int findUserConfig(@RequestBody Map<String,String> body){
        User user;
        if(body.containsKey("username")){
            String username = body.get("username");
            user = userRepository.findUserByUsername(username);
        }
        else if(body.containsKey("email")){
            String email = body.get("email");
            user = userRepository.findUserByEmail(email);
        }
        else user = null;


        return user.getHome_config();

    }

    @PutMapping(value = "/users/homeConfigChanged/{username}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User update(@PathVariable String username,@RequestBody Map<String,String> body){
        String receivedUsername = username;
        User user = userRepository.findUserByUsername(username);
        user.setHome_config(1);
        userRepository.save(user);
        return user;
    }

    @PutMapping(value = "/devices/activityStatusChanged/{device_id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Device turn_on_off(@PathVariable Integer device_id,@RequestBody Map<String,String> body){

        Device device = deviceRepository.findOne(device_id);
        if(device.getDevice_activity_status()==0){
            device.setDevice_activity_status(1);
        }
        else if(device.getDevice_activity_status()==1){
            device.setDevice_activity_status(0);
            device.setDevice_runtime(Double.parseDouble(body.get("device_runtime")));
        }

        deviceRepository.save(device);

        return device;

    }




}