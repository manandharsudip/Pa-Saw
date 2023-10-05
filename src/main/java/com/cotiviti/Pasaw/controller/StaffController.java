package com.cotiviti.Pasaw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.impl.StaffServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/staff/order")
public class StaffController {

    private final StaffServiceImpl staffService;
    
    @PutMapping("/update/{orderId}")
    public ResponseEntity<HttpStatus> updateOrderStatus(@PathVariable(name="orderId") Long orderId, @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody OrderDto orderDto){
        return staffService.updateOrderStatusById(orderId, userPrincipal, orderDto);
    }


}
