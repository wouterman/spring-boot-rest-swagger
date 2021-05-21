package com.github.wouterman.spring.boot.rest.domain;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAll() {
    Iterable<User> users = userRepository.findAll();
    return StreamSupport.stream(users.spliterator(), false)
        .collect(Collectors.toList());
  }

  public User findById(long id) {
    Optional<User> found = userRepository.findById(id);
    return found.orElseThrow(() -> new UserNotFoundException("Could not find user with id " + id));
  }

  public User modifyUser(User user) {
    log.info("Finding user for id {}.", user.getId());
    if (userRepository.existsById(user.getId())) {
      user.setModificationTimestamp(ZonedDateTime.now());
      return userRepository.save(user);
    } else {
      throw new UserNotFoundException("Could not find user with id " + user.getId());
    }

  }
}
