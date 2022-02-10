package com.nuclei.communication.controller;

import com.nuclei.communication.dto.response.ApiResponseDto;
import com.nuclei.communication.exceptions.ApplicationException;
import com.nuclei.communication.exceptions.AuthorizationException;
import com.nuclei.communication.exceptions.ValidationException;
import com.nuclei.communication.utils.GeneralUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Controller exception handler.
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
  
  GeneralUtils generalUtils;
  
  @ExceptionHandler({ValidationException.class, AuthorizationException.class})
  public ResponseEntity<ApiResponseDto> handleCustomerException(ApplicationException applicationException) {
    return generalUtils.apiResponseEntityBuilder(applicationException.getStatusCode(),
        applicationException.getMessage(), null);
  }
}