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
   * Custom Exception Constructor supporting message.
   *
   * @param str       the str
   * @param throwable the throwable
   */
  public CustomException(final String str, final Throwable throwable) {
    // calling the constructor of parent Exception
    super(str, throwable);
  }
}
