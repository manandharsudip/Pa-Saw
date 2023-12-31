package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.dto.CartDto;
import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.dto.ProductResponseDto;
import com.cotiviti.Pasaw.entity.CartEntity;
import com.cotiviti.Pasaw.entity.ProductEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.CartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import org.springframework.web.bind.annotation.CrossOrigin;

// @CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/cart")
public class CartController {

  private final CartService cartService;

  @PostMapping("/addToCart")
  public ResponseEntity<HttpStatus> addToCart(
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    @RequestBody OrderDto orderDto
  ) {
    return cartService.addToCart(userPrincipal, orderDto);
  }

  @PutMapping("/updateToCart")
  public ResponseEntity<HttpStatus> updateToCart(
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    @RequestBody List<CartDto> cartDto
  ) {
    // for (CartDto product : cartDto) {
    //     System.out.println("Cart ID: "+ product.getOrderid() + " Quantity: " + product.getQuantity());
    // }
    return cartService.updateToCart(cartDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<List<CartEntity>> getCartItemsByUserId(
    @PathVariable("id") Long id
  ) {
    return cartService.getCartItemsByUserId(id);
  }

  @GetMapping("pending/{id}")
  public ResponseEntity<List<CartEntity>> getCartItemsByUserIdUnProceeded(
    @PathVariable("id") Long id
  ) {
    return cartService.getCartItemsByUserIdUnProceeded(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteCartItemById(
    @PathVariable("id") Long id
  ) {
    return cartService.deleteById(id);
  }
}
