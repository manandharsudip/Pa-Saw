package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.model.LoginRequest;
import com.cotiviti.Pasaw.model.LoginResponse;
import com.cotiviti.Pasaw.security.JwtIssuer;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final JwtIssuer jwtIssuer; // injection assisted by @RequiredArgsConstructor: similar to creating a constructor and initializing things

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    var token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
    return LoginResponse.builder().accessToken(token).build();
  }
}
