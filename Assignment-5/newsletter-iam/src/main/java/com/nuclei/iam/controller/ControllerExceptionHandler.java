package com.nuclei.iam.controller;

import com.nuclei.iam.dto.response.ApiResponseDto;
import com.nuclei.iam.exceptions.ApplicationException;
import com.nuclei.iam.exceptions.AuthorizationException;
import com.nuclei.iam.exceptions.CustomerException;
import com.nuclei.iam.exceptions.ValidationException;
import com.nuclei.iam.utils.GeneralUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Controller exception handler.
 */
@ControllerAdvice
@AllArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
  
  /**
   * The General utils.
   */
  private final GeneralUtils generalUtils;
  
  /**
   * Handle customer exception response entity.
   *
   * @param applicationException the application exception
   *
   * @return the response entity
   */
  @ExceptionHandler({AuthorizationException.class,
      CustomerException.class, ValidationException.class})
  public ResponseEntity<ApiResponseDto> handleCustomerException(
      ApplicationException applicationException) {
    return generalUtils.apiResponseEntityBuilder(applicationException.getStatusCode(),
        applicationException.getMessage(), applicationException);
  }
}