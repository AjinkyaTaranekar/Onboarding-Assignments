package com.nuclei.assignment.exception;

/**
 * Database Exception.
 */
public class DatabaseException extends Exception {
  /**
   * Database Exception Constructor supporting message.
   *
   * @param str the str
   */
  public DatabaseException(final String str) {
    // calling the constructor of parent Exception
    super(str);
  }
  
  /**
   * Database Exception Constructor supporting message.
   *
   * @param str       the str
   * @param throwable the throwable
   */
  public DatabaseException(final String str, final Throwable throwable) {
    // calling the constructor of parent Exception
    super(str, throwable);
  }
}
