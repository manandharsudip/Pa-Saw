package com.cotiviti.Pasaw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.entity.OrdersEntity;
import com.cotiviti.Pasaw.model.OrderStatus;
import com.cotiviti.Pasaw.repository.OrderRepository;
import com.cotiviti.Pasaw.repository.ProductRepository;
import com.cotiviti.Pasaw.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {
    
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addOrder(OrderDto orderDto){
        OrdersEntity orderEntity = new OrdersEntity();
        // actually product id is enough here user id can be extracted from active user session
        orderEntity.setUserEntity(userRepository.findById(orderDto.getUserid()).orElseThrow());
        orderEntity.setProductEntity(productRepository.findById(orderDto.getProductid()).orElseThrow());
        orderEntity.setStatus(OrderStatus.PENDING);
        orderEntity.setCreated_date(new Date());
        orderEntity.setUpdated_date(new Date());
        orderRepository.save(orderEntity);
    }


    public ResponseEntity<List<OrdersEntity>> getAllOrders(){
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<OrdersEntity>> getPendingOrders(){
        return new ResponseEntity<>(orderRepository.findByStatus(OrderStatus.PENDING), HttpStatus.OK);
    }

    public ResponseEntity<List<OrdersEntity>> getDeliveryOrders(){
        return new ResponseEntity<>(orderRepository.findByStatus(OrderStatus.SENT_FOR_DELIVERY), HttpStatus.OK);
    }

    public ResponseEntity<List<OrdersEntity>> getCompletedOrders(){
        List <OrdersEntity> orderEntity1 = orderRepository.findByStatus(OrderStatus.DELIVERD);
        List <OrdersEntity> orderEntity2 = orderRepository.findByStatus(OrderStatus.CLOSED);
        List <OrdersEntity> orderEntity3 = orderRepository.findByStatus(OrderStatus.CANCELLED);
        List <OrdersEntity> orderEntity4 = orderRepository.findByStatus(OrderStatus.RECIEVED);
        List <OrdersEntity> ordersEntities = new ArrayList<>(orderEntity1);
        // ordersEntities.addAll(orderEntity1);
        ordersEntities.addAll(orderEntity2);
        ordersEntities.addAll(orderEntity3);
        ordersEntities.addAll(orderEntity4);
        return new ResponseEntity<>(ordersEntities, HttpStatus.OK);
    }

}
