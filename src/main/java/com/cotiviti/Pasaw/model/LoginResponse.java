package com.cotiviti.Pasaw.model;

import com.cotiviti.Pasaw.security.UserPrincipal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
  private final String accessToken;
  private final UserPrincipal userPrincipal;
}
