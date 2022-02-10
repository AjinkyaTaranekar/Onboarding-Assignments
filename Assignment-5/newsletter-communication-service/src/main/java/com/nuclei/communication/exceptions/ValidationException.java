package com.nuclei.communication.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The type Validation exception.
 */
@Getter
public class ValidationException extends ApplicationException {
  
  private final HttpStatus statusCode;
  
  /**
   * Instantiates a new Validation exception.
   *
   * @param str        the str
   * @param statusCode the status code
   */
  public ValidationException(final String str, final HttpStatus statusCode) {
    super(str, statusCode);
    this.statusCode = statusCode;
  }
  
  /**
   * Instantiates a new Validation exception.
   *
   * @param str        the str
   * @param statusCode the status code
   * @param throwable  the throwable
   */
  public ValidationException(final String str, final HttpStatus statusCode,
                             final Throwable throwable) {
    super(str, statusCode, throwable);
    this.statusCode = statusCode;
  }
}