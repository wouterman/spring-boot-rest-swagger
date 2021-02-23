package com.github.wouterman.spring.boot.rest.controller;

import com.github.wouterman.spring.boot.rest.model.ApiError;
import com.github.wouterman.spring.boot.rest.model.ResourceNotFoundException;
import com.github.wouterman.spring.boot.rest.model.User;
import com.github.wouterman.spring.boot.rest.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "user", description = "User controller.")
@RestController
public class UserController {

  private final UserRepository userRepository;

  @Autowired()
  public UserController(UserRepository userRepository) {
    this.userRepository = Objects.requireNonNull(userRepository);
  }

  @Operation(description = "Fetch all users.")
  @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<User>> users() {
    log.info("Finding all users.");
    Iterable<User> users = userRepository.findAll();
    List<User> asList = StreamSupport.stream(users.spliterator(), false)
        .collect(Collectors.toList());
    return new ResponseEntity<>(asList, HttpStatus.OK);
  }

  @Operation(description = "user get operation")
  @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> user(@PathVariable long id) {
    log.info("Finding user for id {}.", id);
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      log.info("Found user.");
      return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
    log.info("User not found.");
    throw new ResourceNotFoundException("Could not find resource with id " + id);
  }

  @Operation(description = "user operation")
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class)))
  @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> userPut(@PathVariable long id, @RequestBody User user) {
    log.info("Finding user for id {}.", id);
    Optional<User> found = userRepository.findById(id);
    if (found.isPresent()) {
      log.info("User found. Overwriting with {}.", user);
      user.setModificationTimestamp(ZonedDateTime.now());
      User persisted = userRepository.save(user);
      return new ResponseEntity<>(persisted, HttpStatus.OK);
    }
    log.info("User not found.");
    throw new ResourceNotFoundException("Could not find resource with id " + id);
  }

}
