package com.github.wouterman.spring.boot.rest.controller;

import com.github.wouterman.spring.boot.rest.service.JsonWebTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private static final String TOKEN_PREFIX = "Bearer ";
  private static final String HEADER_STRING = "Authorization";

  private final JsonWebTokenService tokenService;

  public JwtAuthorizationFilter(AuthenticationManager authManager,
      JsonWebTokenService tokenService) {
    super(authManager);
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(HEADER_STRING);

    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }

    doAuthentication(req);
    chain.doFilter(req, res);
  }

  private void doAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      Optional<Jws<Claims>> optionalJws = tokenService
          .parseTokenString(token.replace(TOKEN_PREFIX, ""));
      optionalJws.ifPresent(jws -> {
        String user = jws.getBody().getSubject();
        if (user != null) {
          var authToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      });
    }
  }
}
