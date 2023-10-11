package com.cotiviti.Pasaw.dto;

import com.cotiviti.Pasaw.model.Status;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

  private Long produtId;
  private String productname;
  private Long categoryid;
  private String categoryname;
  private String description;
  private Long userId;
  private Float price;
  private String imageurl;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
}
