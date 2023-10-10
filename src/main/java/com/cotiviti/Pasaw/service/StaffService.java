package com.cotiviti.Pasaw.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.security.UserPrincipal;

public interface StaffService {

    ResponseEntity<HttpStatus> updateOrderStatusById(
            Long orderId,
            UserPrincipal principal,
            OrderDto orderDto);

    void sendToDelivery();

}