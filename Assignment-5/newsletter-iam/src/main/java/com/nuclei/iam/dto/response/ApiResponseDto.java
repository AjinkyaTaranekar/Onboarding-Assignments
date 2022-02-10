package com.nuclei.iam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Api response dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {
  
  /**
   * The Message.
   */
  private String message;
  /**
   * The Body.
   */
  private Object body;
}
