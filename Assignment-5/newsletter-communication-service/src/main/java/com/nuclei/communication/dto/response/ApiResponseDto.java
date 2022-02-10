package com.nuclei.communication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Api response dto.
 */
@Getter
@Setter
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
