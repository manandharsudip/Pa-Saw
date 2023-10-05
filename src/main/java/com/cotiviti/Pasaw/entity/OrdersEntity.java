package com.cotiviti.Pasaw.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cotiviti.Pasaw.model.OrderStatus;
// import com.cotiviti.Pasaw.model.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrdersEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "user_info", nullable = false)
  UserEntity userEntity;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "product_info", nullable = false)
  ProductEntity productEntity;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "staff_info", nullable = true)
  UserEntity staffEntity;

  @Enumerated(EnumType.ORDINAL)
  private OrderStatus status;

  private Date created_date;

  private Date updated_date;
}
