package org.swe632.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.swe632.models.User;
import org.swe632.repositories.UserRepository;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getmessage")
    @ResponseBody
    public String getAllCounters(){
        return "ISP - Hello from backend!";
    }


    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestParam String email, @RequestParam String password){

        boolean emailExists = userRepository.existsByEmail(email);
        User user = userRepository.findByEmailAndPassword(email, password);
        return user;
    }


}