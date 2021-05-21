package com.github.wouterman.spring.boot.rest.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final JsonWebTokenService tokenService;

  @Autowired
  public SecurityConfiguration(
      JsonWebTokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
        .antMatchers(HttpMethod.GET, "/users").permitAll()
        .antMatchers(HttpMethod.GET, "/**/*swagger*/**").permitAll()
        .antMatchers(HttpMethod.GET, "/**/v3/api-docs/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(new JwtAuthorizationFilter(authenticationManager(), tokenService))
        // this disables session creation on Spring Security
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // CSRF blocks post requests to /authenticate.
        // No cookies so don't need it.
        .csrf().disable();
  }
}
