package com.github.wouterman.spring.boot.rest.controller;

import com.github.wouterman.spring.boot.rest.model.ApiError;
import com.github.wouterman.spring.boot.rest.model.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    log.info("Caught {}.", ex.getClass());
    ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    log.info("Returning {}.", error);
    return error.toResponseEntity();
  }

  @Override
  public ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    log.info("Caught {}.", ex.getClass());
    ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
    log.info("Returning {}.", error);
    return error.toResponseEntity();
  }

  @Override
  public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    log.info("Caught {}.", ex.getClass());
    ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
    log.info("Returning {}.", error);
    return error.toResponseEntity();
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
    log.info("Caught {}.", ex.getClass());
    ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    log.info("Returning {}.", error);
    return error.toResponseEntity();
  }


}
