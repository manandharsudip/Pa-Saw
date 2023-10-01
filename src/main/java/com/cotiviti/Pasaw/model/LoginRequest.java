package com.cotiviti.Pasaw.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder // what is this?
public class LoginRequest {

  private String email;
  private String password;
}
