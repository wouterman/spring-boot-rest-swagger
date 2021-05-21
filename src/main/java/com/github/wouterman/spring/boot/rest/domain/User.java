package com.github.wouterman.spring.boot.rest.domain;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {

  public User(String name, String role, int age) {
    this.name = name;
    this.role = role;
    this.age = age;
    this.modificationTimestamp = ZonedDateTime.now();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String role;
  private int age;
  private ZonedDateTime modificationTimestamp;

}
