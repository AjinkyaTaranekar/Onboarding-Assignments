package com.nuclei.example.controller;

import com.nuclei.example.exceptions.InventoryException;
import com.nuclei.example.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

/**
 * The type Controller exception handler.
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
  
  private HashMap<String, String> getErrorResponse (Exception exception) {
    HashMap<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", exception.getMessage());
    return errorResponse;
  }
  
  /**
   * Handle inventory exception response entity.
   *
   * @param ex the ex
   *
   * @return the response entity
   */
  @ExceptionHandler(value = InventoryException.class)
  public ResponseEntity<?> handleInventoryException (InventoryException ex) {
    return ResponseEntity.status(ex.getStatusCode()).body(getErrorResponse(ex));
  }
  
  /**
   * Handle validation exception response entity.
   *
   * @param ex the ex
   *
   * @return the response entity
   */
  @ExceptionHandler(value = ValidationException.class)
  public ResponseEntity<?> handleValidationException (ValidationException ex) {
    return ResponseEntity.status(ex.getStatusCode()).body(getErrorResponse(ex));
  }
  
}