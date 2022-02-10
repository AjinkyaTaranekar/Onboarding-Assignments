package com.nuclei.iam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Otp email dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtpEmailDto {
  
  /**
   * The Email id.
   */
  private String emailId;
  /**
   * The Name.
   */
  private String name;
  
  /**
   * The Body.
   */
  private String body;
  /**
   * The Subject.
   */
  private String subject;
  
  /**
   * The Otp.
   */
  private Integer otp;
  
  /**
   * The Otp expiry time.
   */
  private Integer otpExpiryTime;
}
