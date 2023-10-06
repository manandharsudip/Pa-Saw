package com.cotiviti.Pasaw.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cotiviti.Pasaw.entity.UserEntity;
import com.cotiviti.Pasaw.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<HttpStatus> registerNewCustomer(UserEntity user) {

    Optional<UserEntity> oldUser = userRepository.findByEmail(
      user.getEmail()
    );

    // if (oldUser.isPresent()) {
    //   throw new Exception("User already Used", null);
    // }

    if (!oldUser.isPresent()) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_CUSTOMER");
        user.setCreated_date(new Date());
        user.setUpdated_date(new Date());
        userRepository.save(user);
    }

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
