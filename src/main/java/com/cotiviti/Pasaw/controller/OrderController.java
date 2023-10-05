package com.cotiviti.Pasaw.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.entity.OrdersEntity;
import com.cotiviti.Pasaw.service.impl.OrderServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/order")
public class OrderController {

    private final OrderServiceImpl orderService;
    
    @PostMapping("/addOrder")
    public void addOrder(@RequestBody OrderDto OrderDto){
        System.out.println("Product ID: "+ OrderDto.getProductid());
        System.out.println("Order ID: "+ OrderDto.getUserid());
        System.out.println("Status: "+ OrderDto.getStatus());
        orderService.addOrder(OrderDto);
    }


    @GetMapping
    public ResponseEntity<List<OrdersEntity>> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/pending")
    public ResponseEntity<List<OrdersEntity>> getPendingOrders(){
        return orderService.getPendingOrders();
    }

    @GetMapping("/delivery")
    public ResponseEntity<List<OrdersEntity>> getDeliveryOrders(){
        return orderService.getDeliveryOrders();
    }

    @GetMapping("/completed")
    public ResponseEntity<List<OrdersEntity>> getCompletedOrders(){
        return orderService.getCompletedOrders();
    }



}
