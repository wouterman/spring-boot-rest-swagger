package com.github.wouterman.spring.boot.rest;

import com.github.wouterman.spring.boot.rest.api.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public UserMapper userMapper() {
    return UserMapper.INSTANCE;
  }

}
