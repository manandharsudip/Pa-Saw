package com.cotiviti.Pasaw.service.impl;

import com.cotiviti.Pasaw.dto.UserDTO;
import com.cotiviti.Pasaw.entity.UserEntity;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.CustomerService;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public ResponseEntity<HttpStatus> registerNewCustomer(UserEntity user)
    throws Exception {
    Optional<UserEntity> oldUser = userRepository.findByEmail(user.getEmail());

    if (oldUser.isPresent()) {
      throw new Exception("Email already Used", null);
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("ROLE_CUSTOMER");
    user.setCreated_date(new Date());
    user.setUpdated_date(new Date());
    userRepository.save(user);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<UserEntity> getUserById(UserPrincipal principal) {
    UserEntity user = userRepository
      .findById(principal.getUserId())
      .orElseThrow();

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<HttpStatus> updateUser(@AuthenticationPrincipal UserPrincipal userPrincipal, UserDTO user) throws Exception {
    UserEntity userEntity = userRepository.findById(userPrincipal.getUserId()).orElseThrow();

    userEntity.setAddress(user.getAddress());
    userEntity.setPhonenumber(user.getPhonenumber());
    userRepository.save(userEntity);
    
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }
}
