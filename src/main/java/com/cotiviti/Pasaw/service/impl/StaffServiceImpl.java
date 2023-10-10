package com.cotiviti.Pasaw.service.impl;

import com.cotiviti.Pasaw.dto.OrderDto;
// import com.cotiviti.Pasaw.entity.OrdersEntity;
import com.cotiviti.Pasaw.repository.OrderRepository;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.StaffService;

import lombok.RequiredArgsConstructor;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;

  @Override
  public ResponseEntity<HttpStatus> updateOrderStatusById(
    Long orderId,
    UserPrincipal principal,
    OrderDto orderDto
  ) {
    orderRepository
      .findById(orderId)
      .map(order -> {
        order.setStaffEntity(userRepository.findById(principal.getUserId()).orElseThrow());
        order.setStatus(orderDto.getStatus());
        order.setUpdated_date(new Date());
        return orderRepository.save(order);
      });

      return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public void sendToDelivery() {
    //
  }
}
