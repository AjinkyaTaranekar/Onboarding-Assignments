package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.exception.CustomException;

/**
 * Input Validation interface.
 */
public interface InputValidation {
  
  
  /**
   * validate raw String string.
   *
   * @param string the string
   * @throws CustomException the custom exception
   */
  void validateString(String string) throws CustomException;
  
  /**
   * validate raw String id.
   *
   * @param id the id
   * @throws CustomException the custom exception
   */
  void validateNumeric(String id) throws CustomException;
  
  /**
   * validate raw String details.
   *
   * @param details the details
   * @throws CustomException the custom exception
   */
  void validateDetails(String... details) throws CustomException;
  
}
