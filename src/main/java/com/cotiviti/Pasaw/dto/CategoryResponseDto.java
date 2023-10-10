package com.cotiviti.Pasaw.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {

  private Long categoryId;
  private String categoryname;
  private String description;
  private String imageurl;
  private Long addedBy;
}
