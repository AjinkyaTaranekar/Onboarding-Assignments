package com.nuclei.assignment.exception;

/**
 * Input Exception.
 */
public class InputException extends Exception {
  /**
   * Input Exception Constructor supporting message.
   *
   * @param str the str
   */
  public InputException(final String str) {
    // calling the constructor of parent Exception
    super(str);
  }
  
  /**
   * Input Exception Constructor supporting message.
   *
   * @param str       the str
   * @param throwable the throwable
   */
  public InputException(final String str, final Throwable throwable) {
    // calling the constructor of parent Exception
    super(str, throwable);
  }
}
