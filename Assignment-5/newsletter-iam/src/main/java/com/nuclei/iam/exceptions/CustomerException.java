package com.nuclei.iam.exceptions;

import org.springframework.http.HttpStatus;

/**
 * The type Customer exception.
 */
public class CustomerException extends ApplicationException {
  
  /**
   * Instantiates a new Customer exception.
   *
   * @param str        the str
   * @param statusCode the status code
   */
  public CustomerException(final String str, final HttpStatus statusCode) {
    super(str, statusCode);
  }
  
  /**
   * Instantiates a new Customer exception.
   *
   * @param str        the str
   * @param statusCode the status code
   * @param throwable  the throwable
   */
  public CustomerException(final String str, final HttpStatus statusCode,
                           final Throwable throwable) {
    super(str, statusCode, throwable);
  }
}