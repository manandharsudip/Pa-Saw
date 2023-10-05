package com.cotiviti.Pasaw.entity;

import com.cotiviti.Pasaw.model.CartStatus;
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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cart")
public class CartEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "product_info", nullable = false)
  private ProductEntity productEntity;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "user_info", nullable = false)
  private UserEntity userEntity;

  private Long quantity;

  @Enumerated(EnumType.ORDINAL)
  private CartStatus status;
}
