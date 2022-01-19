package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.exception.CustomException;


/**
 * Input Validation interface.
 */
public interface InputValidation {
  
  
  /**
   * Validate raw String name.
   *
   * @param name the fullName
   * @throws CustomException the custom exception
   */
  void validateString(String name) throws CustomException;
  
  /**
   * Validate raw String numeric.
   *
   * @param number the number
   * @throws CustomException the custom exception
   */
  void validateNumeric(String number) throws CustomException;
  
  /**
   * Validate raw String courses.
   *
   * @param courses the courses
   * @throws CustomException the custom exception
   */
  void validateCourses(String... courses) throws CustomException;
  
  /**
   * Validate raw String columnNumber.
   *
   * @param columnNumber the column number
   * @throws CustomException the custom exception
   */
  void validateColumnNumberForSorting(int columnNumber) throws CustomException;
  
  /**
   * Validate user.
   *
   * @param user the user
   */
  void validateUser(UserEntity user);
  
}
