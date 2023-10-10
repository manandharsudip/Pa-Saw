package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.entity.UserEntity;
import com.cotiviti.Pasaw.model.LoginRequest;
import com.cotiviti.Pasaw.model.LoginResponse;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.AuthService;
import com.cotiviti.Pasaw.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class CustomerController {

  private final AuthService authService;
  private final CustomerService customerService;


  @GetMapping("/myInfo")
  public ResponseEntity<UserEntity> getUserById(@AuthenticationPrincipal UserPrincipal userPrincipal){
    return customerService.getUserById(userPrincipal);
  }


  @PostMapping("/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    return authService.loginAttempt(request.getEmail(), request.getPassword());
  }

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> register(@RequestBody UserEntity customer) throws Exception {
    return customerService.registerNewCustomer(customer);
  }
}
