package com.cotiviti.Pasaw.exception;

public class AuthenticationFailedError extends RuntimeException {

  public AuthenticationFailedError(String msg) {
    super(msg);
  }
}
