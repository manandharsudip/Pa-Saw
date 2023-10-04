package com.cotiviti.Pasaw.service;

import com.cotiviti.Pasaw.entity.UserEntity;
import java.util.Optional;

public interface UserService {
  public Optional<UserEntity> findByEmail(String email);
}
