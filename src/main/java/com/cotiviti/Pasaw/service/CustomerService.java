package com.cotiviti.Pasaw.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.cotiviti.Pasaw.dto.UserDTO;
import com.cotiviti.Pasaw.entity.UserEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;

public interface CustomerService {

    ResponseEntity<HttpStatus> registerNewCustomer(UserEntity user) throws Exception;

    ResponseEntity<HttpStatus> updateUser(@AuthenticationPrincipal UserPrincipal userPrincipal, UserDTO user) throws Exception;

    ResponseEntity<UserEntity> getUserById(UserPrincipal prinicipal);

}