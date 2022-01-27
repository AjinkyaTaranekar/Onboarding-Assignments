package com.nuclei.assignment.service;

import com.nuclei.assignment.exception.InputException;

/**
 * The interface Item validation.
 */
public interface ItemValidation {
  
  /**
   * validate raw String string.
   *
   * @param string the string
   * @throws InputException the input exception
   */
  void validateString(String string) throws InputException;
  
  /**
   * validate raw String id.
   *
   * @param id the id
   * @throws InputException the input exception
   */
  void validateNumeric(String id) throws InputException;
  
}
