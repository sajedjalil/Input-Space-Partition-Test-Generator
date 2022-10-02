package org.swe632.controllers;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    @GetMapping("/getmessage")
    @ResponseBody
    public String getAllCounters(){
        return "Hello from backend!";
    }

}