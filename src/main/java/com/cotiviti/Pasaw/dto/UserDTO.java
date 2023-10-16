package com.cotiviti.Pasaw.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
  private Long id;
  private String email;
  private String firstname;
  private String lastname;
  private Long phonenumber;
  private String address;
}
