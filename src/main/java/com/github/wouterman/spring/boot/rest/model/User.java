package com.github.wouterman.spring.boot.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
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
@Schema(name = "User", description = "User response entity.", accessMode = AccessMode.READ_WRITE)
public class User {

  public User(String name, String role, int age) {
    this.name = name;
    this.role = role;
    this.age = age;
    this.modificationTimestamp = ZonedDateTime.now();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Schema(description = "User name.")
  private String name;
  @Schema(description = "User role.")
  private String role;
  @Schema(description = "User age.")
  private int age;
  @Schema(accessMode = AccessMode.READ_ONLY, description = "Modification date.")
  private ZonedDateTime modificationTimestamp;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
