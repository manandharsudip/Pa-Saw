package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.entity.UserEntity;
import com.cotiviti.Pasaw.model.LoginRequest;
import com.cotiviti.Pasaw.model.LoginResponse;
import com.cotiviti.Pasaw.service.AuthService;
import com.cotiviti.Pasaw.service.impl.CustomerServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

  private final AuthService authService;
  private final CustomerServiceImpl customerService;

  @PostMapping("/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    return authService.loginAttempt(request.getEmail(), request.getPassword());
  }

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> register(@RequestBody UserEntity customer) {
    return customerService.registerNewCustomer(customer);
  }
}
