package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.entity.CartEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.impl.CartServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/cart")
public class CartController {

  private final CartServiceImpl cartService;

  @PostMapping("/addToCart")
  public ResponseEntity<HttpStatus> addToCart(
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    @RequestBody OrderDto orderDto
  ) {
    return cartService.addToCart(userPrincipal, orderDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<List<CartEntity>> getCartItemsByUserId(
    @PathVariable("id") Long id
  ) {
    return cartService.getCartItemsByUserId(id);
  }
}
