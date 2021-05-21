package com.github.wouterman.spring.boot.rest.domain;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    this(message, null);
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}