package com.nuclei.example.exceptions;

import lombok.Getter;

/**
 * The type Validation exception.
 */
@Getter
public class ValidationException extends Exception {
  /**
   * The Status code.
   */
  int statusCode;
  
  /**
   * Instantiates a new Validation exception.
   *
   * @param str        the str
   * @param statusCode the status code
   */
  public ValidationException (final String str, final Integer statusCode) {
    // calling the constructor of parent Exception
    super(str);
    this.statusCode = statusCode;
  }
  
  /**
   * Instantiates a new Validation exception.
   *
   * @param str        the str
   * @param statusCode the status code
   * @param throwable  the throwable
   */
  public ValidationException (final String str, final Integer statusCode,
                              final Throwable throwable) {
    // calling the constructor of parent Exception
    super(str, throwable);
    this.statusCode = statusCode;
  }
}