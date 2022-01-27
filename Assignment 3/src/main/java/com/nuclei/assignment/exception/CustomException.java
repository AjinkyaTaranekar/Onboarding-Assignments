package com.nuclei.assignment.exception;

/**
 * Custom Exception.
 */
public class CustomException extends Exception {
  /**
   * Custom Exception Constructor supporting message.
   *
   * @param str the str
   */
  public CustomException(final String str) {
    // calling the constructor of parent Exception
    super(str);
  }
  
  /**
   * Instantiates a new Custom exception.
   *
   * @param str       the str
   * @param exception the exception
   */
  public CustomException(final String str, Throwable exception) {
    // calling the constructor of parent Exception
    super(str, exception);
  }
}
