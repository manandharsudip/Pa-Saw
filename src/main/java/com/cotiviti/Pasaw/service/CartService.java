package com.cotiviti.Pasaw.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.entity.CartEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;

public interface CartService {

    ResponseEntity<HttpStatus> addToCart(UserPrincipal userPrincipal, OrderDto orderDto);

    ResponseEntity<List<CartEntity>> getCartItemsByUserId(Long id);

    ResponseEntity<List<CartEntity>> getCartItemsByUserIdUnProceeded(Long id);

}