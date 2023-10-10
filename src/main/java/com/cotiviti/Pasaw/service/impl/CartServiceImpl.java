package com.cotiviti.Pasaw.service.impl;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.entity.CartEntity;
import com.cotiviti.Pasaw.exception.ResourceNotFoundException;
import com.cotiviti.Pasaw.model.CartStatus;
import com.cotiviti.Pasaw.repository.CartRepository;
import com.cotiviti.Pasaw.repository.ProductRepository;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.CartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  @Override
  public ResponseEntity<HttpStatus> addToCart(
    UserPrincipal userPrincipal,
    OrderDto orderDto
  ) {
    CartEntity cartEntity = new CartEntity();
    cartEntity.setUserEntity(
      userRepository
        .findById(userPrincipal.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("Could not find User"))
    );
    cartEntity.setProductEntity(
      productRepository
        .findById(orderDto.getProductid())
        .orElseThrow(() ->
          new ResourceNotFoundException("Could not find Product")
        )
    );
    cartEntity.setQuantity(orderDto.getQuantity());
    cartEntity.setStatus(CartStatus.NOTCHECKOUT);
    cartRepository.save(cartEntity);
    return new ResponseEntity<HttpStatus>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<CartEntity>> getCartItemsByUserId(Long id) {
    List<CartEntity> cartEntity = cartRepository.findByUserEntity(
      userRepository
        .findById(id)
        .orElseThrow(() ->
          new ResourceNotFoundException("Could not find Products")
        )
    );
    return new ResponseEntity<>(cartEntity, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<CartEntity>> getCartItemsByUserIdUnProceeded(
    Long id
  ) {
    List<CartEntity> cartEntity = cartRepository.findByUserEntityAndStatus(
      userRepository
        .findById(id)
        .orElseThrow(() ->
          new ResourceNotFoundException("Could not find Products")
        ),
      CartStatus.NOTCHECKOUT
    );
    return new ResponseEntity<>(cartEntity, HttpStatus.OK);
  }
}
