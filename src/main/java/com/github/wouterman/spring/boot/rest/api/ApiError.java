package com.github.wouterman.spring.boot.rest.api;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.time.ZonedDateTime;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Value
@Schema(name = "ApiError", description = "Standard api error response entity.", accessMode = AccessMode.READ_ONLY)
public class ApiError {

  ZonedDateTime timestamp;
  HttpStatus status;
  String errorMessage;

  public ApiError(HttpStatus status, String errorMessage) {
    this.timestamp = ZonedDateTime.now();
    this.status = status;
    this.errorMessage = errorMessage;
  }

  public ResponseEntity<Object> toResponseEntity() {
    return new ResponseEntity<>(this, status);
  }
}
