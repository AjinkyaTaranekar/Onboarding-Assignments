package com.nuclei.assignment.exception;

/**
 * Attribute Parse Exception.
 */
public class AttributeParseException extends Exception {
  /**
   * Attribute Parse Exception Constructor supporting message.
   *
   * @param str the str
   */
  public AttributeParseException(final String str) {
    // calling the constructor of parent Exception
    super(str);
  }
  
  /**
   * Attribute Parse Exception Constructor supporting message.
   *
   * @param str       the str
   * @param throwable the throwable
   */
  public AttributeParseException(final String str, final Throwable throwable) {
    // calling the constructor of parent Exception
    super(str, throwable);
  }
}
