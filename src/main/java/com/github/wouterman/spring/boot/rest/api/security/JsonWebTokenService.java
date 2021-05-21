package com.github.wouterman.spring.boot.rest.api.security;

import com.github.wouterman.spring.boot.rest.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JsonWebTokenService {

  private final Key key;
  private final JwtParser parser;

  public JsonWebTokenService(Key jwtKey, JwtParser jwtParser) {
    this.parser = jwtParser;
    this.key = jwtKey;
  }

  public String createTokenFor(User user) {
    return Jwts.builder()
        .setSubject(user.getName())
        .signWith(key)
        .compact();
  }

  public Optional<Jws<Claims>> parseTokenString(String tokenString) {
    Optional<Jws<Claims>> result = Optional.empty();
    try {
      result = Optional.of(parser.parseClaimsJws(tokenString));
    } catch (JwtException ex) {
      log.warn("Failed to parse token: {}.", tokenString);
      log.warn("Exception: ", ex);
    }
    return result;
  }

}
