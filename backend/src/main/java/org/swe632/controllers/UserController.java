package org.swe632.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.swe632.models.Response;
import org.swe632.models.User;
import org.swe632.repositories.UserRepository;

@RestController(value = "user")
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/getuser")
    @ResponseBody
    public Response getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password){

        boolean emailExists = userRepository.existsByEmail(email);
        if( !emailExists ) return Response.builder().message("No user found with this email.").
                type(Response.Type.FAILED).build();

        User user = userRepository.findByEmailAndPassword(email, password);

        if(user == null) return Response.builder().message("Wrong password.").
                type(Response.Type.FAILED).build();
        else return Response.builder().type(Response.Type.OK).object(user).build();
    }

    @PostMapping("/adduser")
    @ResponseBody
    public Response addUser(@RequestParam String firstName,
                            @RequestParam(required = false) String middleName, @RequestParam(required = false) String lastName,
                            @RequestParam String email, @RequestParam String password){

        boolean emailExists = userRepository.existsByEmail(email);
        if( emailExists ) return Response.builder().message("Email already in use.").type(Response.Type.FAILED).build();

        User user = User.builder().firstName(firstName).middleName(middleName).lastName(lastName)
                .email(email).password(password).build();
        userRepository.save(user);

        return Response.builder().type(Response.Type.OK).object(user).build();
    }
}
