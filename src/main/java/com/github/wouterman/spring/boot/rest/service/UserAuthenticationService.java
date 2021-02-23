package com.github.wouterman.spring.boot.rest.service;

import com.github.wouterman.spring.boot.rest.model.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

  public Optional<User> validateCredentials(String username, String password) {
    Optional<User> result = Optional.empty();
    // Ultra secure password check.
    if ("password".equals(password)) {
      User user = new User();
      user.setName(username);
      result = Optional.of(user);
    }
    return result;
  }

}
