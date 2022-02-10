package com.nuclei.iam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Update customer dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerDto {
  
  /**
   * The Name.
   */
  private String name;
  
  /**
   * The Password.
   */
  private String password;
}
