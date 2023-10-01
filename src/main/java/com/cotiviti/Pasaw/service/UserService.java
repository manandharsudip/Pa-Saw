package com.cotiviti.Pasaw.service;

import java.util.Optional;


import com.cotiviti.Pasaw.entity.UserEntity;


public interface UserService {
    public Optional<UserEntity> findByEmail(String email);
    
}
