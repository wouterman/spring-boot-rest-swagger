package com.github.wouterman.spring.boot.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import com.github.wouterman.spring.boot.rest.model.User;
import com.github.wouterman.spring.boot.rest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@OpenAPIDefinition(info = @Info(title = "OpenApi definition test project"))
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner loadTestData(UserRepository repository) {
    return args -> {
      log.info("Loading test data.");
      for (int i = 0; i < 10; i++) {
        log.info("Inserting user {}", i);
        User user = new User("user" + i, "someRole", i);
        repository.save(user);
      }
      log.info("Test data loaded.");
    };
  }

}
