package com.cotiviti.Pasaw.dto;

import com.cotiviti.Pasaw.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class OrderDto {

  private Long orderid;
  private Long userid;
  private Long productid;
  private List<Long> productList;
  private Long staffid;
  private Long quantity;

  @Enumerated(EnumType.ORDINAL)
  private OrderStatus status;
}
