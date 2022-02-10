package com.nuclei.communication.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The type Application exception.
 */
@Getter
public abstract class ApplicationException extends Exception {
  /**
   * The Status code.
   */
  private final HttpStatus statusCode;
  
  /**
   * Instantiates a new Application exception.
   *
   * @param str        the str
   * @param statusCode the status code
   */
  public ApplicationException(final String str, final HttpStatus statusCode) {
    // calling the constructor of parent Exception
    super(str);
    this.statusCode = statusCode;
  }
  
  /**
   * Instantiates a new Application exception.
   *
   * @param str        the str
   * @param statusCode the status code
   * @param throwable  the throwable
   */
  public ApplicationException(final String str, final HttpStatus statusCode,
                                final Throwable throwable) {
    // calling the constructor of parent Exception
    super(str, throwable);
    this.statusCode = statusCode;
  }
}

