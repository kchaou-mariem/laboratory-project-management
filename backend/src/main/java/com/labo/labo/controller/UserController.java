package com.labo.labo.controller;

import com.labo.labo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/name")
    public String getUser(){
        return userService.getUser();
    }
}
