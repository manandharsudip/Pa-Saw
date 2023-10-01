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


    @GetMapping("/secured")
    public String test(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return "Loggeed In! Congratulations !!!! Hi " + userPrincipal.getUsername();
    }

}
