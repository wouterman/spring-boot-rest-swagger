package com.github.wouterman.spring.boot.rest.controller;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public Key jwtKey(@Value("${jwt.secretkey}") String secretString) {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
  }

  @Bean
  public JwtParser jwtParser(Key jwtKey) {
    return Jwts.parserBuilder()
        .setSigningKey(jwtKey).build();
  }
}
