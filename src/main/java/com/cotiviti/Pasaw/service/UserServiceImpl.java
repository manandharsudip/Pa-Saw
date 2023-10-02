package com.cotiviti.Pasaw.service;

import com.cotiviti.Pasaw.entity.UserEntity;
import com.cotiviti.Pasaw.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Optional<UserEntity> findByEmail(String email) {

    var user = userRepository.findByEmail(email).orElseThrow();

    if (user!=null){
      return Optional.of(user);
    }

    return Optional.empty();
  }
}
