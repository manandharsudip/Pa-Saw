package com.cotiviti.Pasaw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cotiviti.Pasaw.dto.OrderDto;
import com.cotiviti.Pasaw.entity.OrdersEntity;
import com.cotiviti.Pasaw.exception.ResourceNotFoundException;
import com.cotiviti.Pasaw.model.CartStatus;
import com.cotiviti.Pasaw.model.OrderStatus;
import com.cotiviti.Pasaw.repository.CartRepository;
import com.cotiviti.Pasaw.repository.OrderRepository;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public ResponseEntity<HttpStatus> addOrder(UserPrincipal userPrincipal, OrderDto orderDto){

        List<Long> cartList = orderDto.getCartList();

        cartList.forEach(cartid -> {
            cartRepository.findById(cartid).map(items -> {
                OrdersEntity orderEntity = new OrdersEntity();
                // actually product id is enough here user id can be extracted from active user session
                orderEntity.setUserEntity(userRepository.findById(userPrincipal.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found")));
                orderEntity.setProductEntity(items.getProductEntity());
                orderEntity.setStatus(OrderStatus.PENDING);
                orderEntity.setCreated_date(new Date());
                orderEntity.setUpdated_date(new Date());
                items.setStatus(CartStatus.CHECKOUT);
                cartRepository.save(items);
                return orderRepository.save(orderEntity);
            });
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Override
    public ResponseEntity<List<OrdersEntity>> getAllOrders(){
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrdersEntity>> getPendingOrders(){
        return new ResponseEntity<>(orderRepository.findByStatus(OrderStatus.PENDING), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrdersEntity>> getDeliveryOrders(){
        return new ResponseEntity<>(orderRepository.findByStatus(OrderStatus.SENT_FOR_DELIVERY), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrdersEntity>> getCompletedOrders(){
        List <OrdersEntity> orderEntity1 = orderRepository.findByStatus(OrderStatus.DELIVERD);
        List <OrdersEntity> orderEntity2 = orderRepository.findByStatus(OrderStatus.CLOSED);
        List <OrdersEntity> orderEntity3 = orderRepository.findByStatus(OrderStatus.CANCELLED);
        List <OrdersEntity> orderEntity4 = orderRepository.findByStatus(OrderStatus.RECIEVED);
        List <OrdersEntity> ordersEntities = new ArrayList<>(orderEntity1);
        ordersEntities.addAll(orderEntity2);
        ordersEntities.addAll(orderEntity3);
        ordersEntities.addAll(orderEntity4);
        return new ResponseEntity<>(ordersEntities, HttpStatus.OK);
    }

}
