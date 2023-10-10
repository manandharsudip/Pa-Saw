package com.cotiviti.Pasaw.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cotiviti.Pasaw.exception.AuthenticationFailedError;
import com.cotiviti.Pasaw.exception.ResourceNotFoundException;
import com.cotiviti.Pasaw.model.ErrorMessage;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessage> resourceNotFoundException(
    ResourceNotFoundException ex,
    WebRequest request
  ) {
    ErrorMessage message = new ErrorMessage(
      HttpStatus.NOT_FOUND.value(),
      new Date(),
      ex.getMessage(),
      request.getDescription(false)
    );

    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AuthenticationFailedError.class)
  public ResponseEntity<ErrorMessage> authenticationFailedError(
    AuthenticationFailedError ex,
    WebRequest request
  ) {
    ErrorMessage message = new ErrorMessage(
      HttpStatus.FORBIDDEN.value(),
      new Date(),
      ex.getMessage(),
      request.getDescription(false)
    );

    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
  }
}
