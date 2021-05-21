package com.github.wouterman.spring.boot.rest.api;

import com.github.wouterman.spring.boot.rest.domain.User;
import com.github.wouterman.spring.boot.rest.domain.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Objects;
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

  private final UserService userService;

  @Autowired()
  public UserController(UserService userService) {
    this.userService = Objects.requireNonNull(userService);
  }

  @Operation(description = "Fetch all users.")
  @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<User>> users() {
    log.info("Finding all users.");
    List<User> users = userService.findAll();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @Operation(description = "user get operation")
  @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> user(@PathVariable long id) {
    log.info("Finding user for id {}.", id);
    User user = userService.findById(id);
    log.info("Found user.");
    return new ResponseEntity<>(user, HttpStatus.OK);


  }

  @Operation(description = "user operation")
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class)))
  @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> userPut(@PathVariable long id, @RequestBody User user) {
    user.setId(id);
    User persisted = userService.modifyUser(user);
    return new ResponseEntity<>(persisted, HttpStatus.OK);
  }
}
