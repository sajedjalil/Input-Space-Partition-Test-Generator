package org.swe632.controllers;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {


    @GetMapping("/getmessage")
    @ResponseBody
    public String getAllCounters(){
        return "ISP - Hello from backend!";
    }

}