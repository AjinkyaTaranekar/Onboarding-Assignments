package com.nuclei.iam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Login dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
  
  /**
   * The Email id.
   */
  private String emailId;
  /**
   * The Password.
   */
  private String password;
}
