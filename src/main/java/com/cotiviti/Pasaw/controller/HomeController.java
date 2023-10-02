package com.cotiviti.Pasaw.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cotiviti.Pasaw.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {


    @GetMapping("/")
    public String greeting(){
        return "<h1>Welcome to Pa:Saw</h1>";
    }


    @GetMapping("/notsecured")
    public String test(){
        return "Loggeed In! Congratulations !!!! Hi  this can be viewd by anyone";
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return "Hi "+ userPrincipal.getUsername() + " all good? this can only be viewed by admin and user";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return "Hi "+ userPrincipal.getUsername() + " all good? this can only be viewed by admin";
    }

    @GetMapping("/customer")
    public String customer(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return "Hi "+ userPrincipal.getUsername() + " all good? this can only be viewed by customer";
    }

}
