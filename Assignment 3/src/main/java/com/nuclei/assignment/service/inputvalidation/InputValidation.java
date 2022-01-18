package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.exception.CustomException;

/**
 * Input Validation interface.
 */
public interface InputValidation {
  
  
  /**
   * Parse raw String name.
   *
   * @param name the name
   * @throws CustomException the custom exception
   */
  void validateFullName(String name) throws CustomException;
  
  /**
   * Parse raw String id.
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
  
  /**
   * validate data is not null.
   *
   * @param data               the data
   * @param checkedOnAttribute the checked on attribute
   * @throws CustomException the custom exception
   */
  void checkDataIsNull(String data, String checkedOnAttribute) throws CustomException;
  
}
