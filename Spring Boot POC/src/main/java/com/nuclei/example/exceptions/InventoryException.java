package com.nuclei.example.exceptions;

import lombok.Getter;

/**
 * The type Inventory exception.
 */
@Getter
public class InventoryException extends Exception {
  /**
   * The Status code.
   */
  int statusCode;
  
  /**
   * Instantiates a new Inventory exception.
   *
   * @param str        the str
   * @param statusCode the status code
   */
  public InventoryException (final String str, final Integer statusCode) {
    // calling the constructor of parent Exception
    super(str);
    this.statusCode = statusCode;
  }
  
  /**
   * Instantiates a new Inventory exception.
   *
   * @param str        the str
   * @param statusCode the status code
   * @param throwable  the throwable
   */
  public InventoryException (final String str, final Integer statusCode,
                             final Throwable throwable) {
    // calling the constructor of parent Exception
    super(str, throwable);
    this.statusCode = statusCode;
  }
}