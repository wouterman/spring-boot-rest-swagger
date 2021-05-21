package com.github.wouterman.spring.boot.rest.api.security;

public class InvalidCredentialsException extends Exception {

  public InvalidCredentialsException() {
    super("Invalid credentials provided!");
  }
}
