package com.cotiviti.Pasaw.dto;


import org.springframework.web.multipart.MultipartFile;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

  private String categoryname;
  private String description;
  private MultipartFile imageurl;
}
