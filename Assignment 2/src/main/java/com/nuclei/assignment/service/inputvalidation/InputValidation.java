package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;

import java.util.Set;

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
   * Parse raw String data to address.
   *
   * @param address the address
   * @return the string
   * @throws CustomException the custom exception
   */
  String validateAddress(String address) throws CustomException;
  
  /**
   * Parse raw String data to age.
   *
   * @param age the age
   * @return the int
   * @throws CustomException the custom exception
   */
  int validateAge(String age) throws CustomException;
  
  /**
   * Parse raw String data to type.
   *
   * @param type the type
   * @return the set
   * @throws CustomException the custom exception
   */
  Set<Courses> validateCourses(String type) throws CustomException;
  
  /**
   * Parse raw String data to rollNumber.
   *
   * @param rollNumber the roll number
   * @return the int
   * @throws CustomException the custom exception
   */
  int validateRollNumber(String rollNumber) throws CustomException;
  
  /**
   * Parse raw String data to rollNumber.
   *
   * @param columnNumber the column number
   * @return the int
   * @throws CustomException the custom exception
   */
  int validateColumnNumberForSorting(String columnNumber) throws CustomException;
  
  /**
   * Parse raw String data to rollNumber.
   *
   * @param sortingOrder the sorting order
   * @return the sorting order
   * @throws CustomException the custom exception
   */
  SortingOrder validateOrderOfSorting(String sortingOrder) throws CustomException;
}
