package com.nuclei.iam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Create customer dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDto {
  
  /**
   * The Name.
   */
  private String name;
  /**
   * The Email id.
   */
  private String emailId;
  /**
   * The Password.
   */
  private String password;
}
