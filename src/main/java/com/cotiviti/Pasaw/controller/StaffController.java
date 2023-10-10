package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/staff/order")
public class StaffController {

  private final StaffService staffService;

  @PutMapping("/update/{orderId}")
  public ResponseEntity<HttpStatus> updateOrderStatus(
    @PathVariable(name = "orderId") Long orderId,
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    @RequestBody OrderDto orderDto
  ) {
    try {
      return staffService.updateOrderStatusById(
        orderId,
        userPrincipal,
        orderDto
      );
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }
}
