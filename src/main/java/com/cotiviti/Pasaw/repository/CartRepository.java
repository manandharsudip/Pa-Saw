package com.cotiviti.Pasaw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cotiviti.Pasaw.entity.CartEntity;
import com.cotiviti.Pasaw.entity.UserEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long>{
    List<CartEntity> findByUserEntity(UserEntity userId);
}
