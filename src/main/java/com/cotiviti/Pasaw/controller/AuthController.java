package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.model.LoginRequest;
import com.cotiviti.Pasaw.model.LoginResponse;
import com.cotiviti.Pasaw.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    return authService.loginAttempt(request.getEmail(), request.getPassword());
  }
}
