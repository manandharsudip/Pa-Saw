package com.cotiviti.Pasaw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cotiviti.Pasaw.entity.OrdersEntity;
import com.cotiviti.Pasaw.model.OrderStatus;

public interface OrderRepository extends JpaRepository<OrdersEntity, Long>{
    List<OrdersEntity> findByStatus(OrderStatus status);
}
