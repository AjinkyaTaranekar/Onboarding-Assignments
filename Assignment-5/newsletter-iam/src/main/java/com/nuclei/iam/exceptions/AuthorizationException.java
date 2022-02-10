package com.nuclei.iam.exceptions;

import org.springframework.http.HttpStatus;

/**
 * The type Authorization exception.
 */
public class AuthorizationException extends ApplicationException {
  
  /**
   * Instantiates a new Authorization exception.
   *
   * @param str        the str
   * @param statusCode the status code
   */
  public AuthorizationException(final String str, final HttpStatus statusCode) {
    super(str, statusCode);
  }
  
  /**
   * Instantiates a new Authorization exception.
   *
   * @param str        the str
   * @param statusCode the status code
   * @param throwable  the throwable
   */
  public AuthorizationException(final String str, final HttpStatus statusCode,
                                final Throwable throwable) {
    super(str, statusCode, throwable);
  }
}
