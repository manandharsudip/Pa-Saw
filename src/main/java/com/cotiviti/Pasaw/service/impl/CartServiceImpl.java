package com.cotiviti.Pasaw.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.entity.CartEntity;
import com.cotiviti.Pasaw.model.CartStatus;
import com.cotiviti.Pasaw.repository.CartRepository;
import com.cotiviti.Pasaw.repository.ProductRepository;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl {
    
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<HttpStatus> addToCart(UserPrincipal userPrincipal, OrderDto orderDto){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserEntity(userRepository.findById(userPrincipal.getUserId()).orElseThrow());
        cartEntity.setProductEntity(productRepository.findById(orderDto.getProductid()).orElseThrow());
        cartEntity.setQuantity(orderDto.getQuantity());
        cartEntity.setStatus(CartStatus.NOTCHECKOUT);;
        cartRepository.save(cartEntity);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    public ResponseEntity<List<CartEntity>> getCartItemsByUserId(Long id){
        List <CartEntity> cartEntity = cartRepository.findByUserEntity(userRepository.findById(id).orElseThrow());
        return new ResponseEntity<>(cartEntity, HttpStatus.OK);
    }
}
