package com.github.wouterman.spring.boot.rest.api.security;

import com.github.wouterman.spring.boot.rest.domain.User;
import java.util.Base64;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  private final UserAuthenticationService authenticationService;
  private final JsonWebTokenService tokenService;

  public AuthenticationController(
      UserAuthenticationService authenticationService,
      JsonWebTokenService tokenService) {
    this.authenticationService = authenticationService;
    this.tokenService = tokenService;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<JwtToken> authenticate(@RequestHeader("Authorization") String basicAuth)
      throws InvalidCredentialsException {
    // Expect String 'Base <somethingbase64>'.
    String[] authSplit = basicAuth.split(" ");
    if (authSplit.length == 2 && "Basic".equals(authSplit[0])) {
      // Expect String 'username:password'.
      String[] credentials = new String(Base64.getDecoder().decode(authSplit[1])).split(":");
      if (credentials.length == 2) {
        Optional<User> optionalUser = authenticationService
            .validateCredentials(credentials[0], credentials[1]);
        User user = optionalUser.orElseThrow(InvalidCredentialsException::new);
        String tokenString = tokenService.createTokenFor(user);
        return new ResponseEntity<>(new JwtToken(tokenString), HttpStatus.OK);
      }
    }
    throw new IllegalArgumentException("Invalid basic auth format supplied!");
  }
}
