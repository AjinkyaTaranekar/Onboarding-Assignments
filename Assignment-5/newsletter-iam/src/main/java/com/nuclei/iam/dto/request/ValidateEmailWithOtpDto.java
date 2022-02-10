package com.nuclei.iam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Validate email with otp dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidateEmailWithOtpDto {
  
  /**
   * The Email id.
   */
  private String emailId;
  /**
   * The Otp.
   */
  private Integer otp;
}
