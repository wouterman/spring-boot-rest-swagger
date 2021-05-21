package com.github.wouterman.spring.boot.rest.api;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User", description = "User response entity.", accessMode = AccessMode.READ_WRITE)
public class UserDto {

  @Schema(accessMode = AccessMode.READ_ONLY, description = "User id.")
  private Long id;

  @Schema(description = "User name.")
  private String name;
  @Schema(description = "User role.")
  private String role;
  @Schema(description = "User age.")
  private int age;
  @Schema(accessMode = AccessMode.READ_ONLY, description = "Modification date.")
  private ZonedDateTime modificationTimestamp;

}
