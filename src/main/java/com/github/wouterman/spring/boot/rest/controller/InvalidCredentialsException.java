package com.github.wouterman.spring.boot.rest.controller;

public class InvalidCredentialsException extends Exception {

  public InvalidCredentialsException() {
    super("Invalid credentials provided!");
  }
}
