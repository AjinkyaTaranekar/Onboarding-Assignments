package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.exception.CustomException;

/**
 * Input Validation interface.
 */
public interface InputValidation {
  
  
  /**
   * Parse raw String data to name.
   *
   * @param name the name
   * @return the string
   * @throws CustomException the custom exception
   */
  String validateName(String name) throws CustomException;
  
  /**
   * Parse raw String data to id.
   *
   * @param id the id
   * @return the int
   * @throws CustomException the custom exception
   */
  int validateId(String id) throws CustomException;
  
}
