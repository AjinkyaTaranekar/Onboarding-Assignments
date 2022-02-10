package com.nuclei.iam.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Customer dto.
 */
@Getter
@Setter
public class CustomerDto {
  /**
   * The Id.
   */
  private Integer id;
  /**
   * The Name.
   */
  private String name;
  /**
   * The Email id.
   */
  private String emailId;
}
