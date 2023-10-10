package com.cotiviti.Pasaw.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cotiviti.Pasaw.entity.UserEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;

public interface CustomerService {

    ResponseEntity<HttpStatus> registerNewCustomer(UserEntity user) throws Exception;

    ResponseEntity<UserEntity> getUserById(UserPrincipal prinicipal);

}