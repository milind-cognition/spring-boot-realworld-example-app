package io.spring.application.user;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonRootName("user")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Parameters for registering a new user")
public class RegisterParam {
  @NotBlank(message = "can't be empty")
  @Email(message = "should be an email")
  @DuplicatedEmailConstraint
  @Schema(description = "Email address of the user", example = "jake@jake.jake")
  private String email;

  @NotBlank(message = "can't be empty")
  @DuplicatedUsernameConstraint
  @Schema(description = "Username of the user", example = "jake")
  private String username;

  @NotBlank(message = "can't be empty")
  @Schema(description = "Password for the user account", example = "jakejake")
  private String password;
}
