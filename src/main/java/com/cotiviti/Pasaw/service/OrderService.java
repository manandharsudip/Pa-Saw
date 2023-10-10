package com.cotiviti.Pasaw.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.entity.OrdersEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;

public interface OrderService {

    ResponseEntity<HttpStatus> addOrder(UserPrincipal userPrincipal, OrderDto orderDto);

    ResponseEntity<List<OrdersEntity>> getAllOrders();

    ResponseEntity<List<OrdersEntity>> getPendingOrders();

    ResponseEntity<List<OrdersEntity>> getDeliveryOrders();

    ResponseEntity<List<OrdersEntity>> getCompletedOrders();

}