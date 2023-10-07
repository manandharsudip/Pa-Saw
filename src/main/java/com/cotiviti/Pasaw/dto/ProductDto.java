package com.cotiviti.Pasaw.dto;

import com.cotiviti.Pasaw.model.Status;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

  private String productname;
  private Long categoryid;
  private String description;
  private Float price;
  private MultipartFile imageurl;

  @Enumerated(EnumType.ORDINAL)
  private Status status;
}
