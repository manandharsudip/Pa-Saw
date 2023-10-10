package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.entity.OrdersEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/order")
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/addOrder")
  public ResponseEntity<HttpStatus> addOrder(
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    @RequestBody OrderDto orderDto
  ) {
    try {
      return orderService.addOrder(userPrincipal, orderDto);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  public ResponseEntity<List<OrdersEntity>> getAllOrders() {
    try {
      return orderService.getAllOrders();
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/pending")
  public ResponseEntity<List<OrdersEntity>> getPendingOrders() {
    try {
      return orderService.getPendingOrders();
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/delivery")
  public ResponseEntity<List<OrdersEntity>> getDeliveryOrders() {
    try {
      return orderService.getDeliveryOrders();
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/completed")
  public ResponseEntity<List<OrdersEntity>> getCompletedOrders() {
    try {
      return orderService.getCompletedOrders();
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }
}
