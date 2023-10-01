package com.cotiviti.Pasaw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String greeting(){
        return "<h1>Welcome to Pa:Saw</h1>";
    }

}
