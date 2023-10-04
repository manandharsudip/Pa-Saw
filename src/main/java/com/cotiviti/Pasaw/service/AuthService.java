package com.cotiviti.Pasaw.service;

import com.cotiviti.Pasaw.model.LoginResponse;

public interface AuthService {
  public LoginResponse loginAttempt(String email, String password);
}
