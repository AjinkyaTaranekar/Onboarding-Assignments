package com.nuclei.assignment.service.inputparser;

import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;

import java.util.Set;

/**
 * Input Parser interface.
 */
public interface InputParser {
  
  
  /**
   * Parse raw String data to name.
   *
   * @param name the name
   * @return the string
   * @throws CustomException the custom exception
   */
  String parseName(String name) throws CustomException;
  
  /**
   * Parse raw String data to address.
   *
   * @param address the address
   * @return the string
   * @throws CustomException the custom exception
   */
  String parseAddress(String address) throws CustomException;
  
  /**
   * Parse raw String data to age.
   *
   * @param age the age
   * @return the int
   * @throws CustomException the custom exception
   */
  int parseAge(String age) throws CustomException;
  
  /**
   * Parse raw String data to type.
   *
   * @param type the type
   * @return the set
   * @throws CustomException the custom exception
   */
  Set<Courses> parseCourses(String type) throws CustomException;
  
  /**
   * Parse raw String data to rollNumber.
   *
   * @param rollNumber the roll number
   * @return the int
   * @throws CustomException the custom exception
   */
  int parseRollNumber(String rollNumber) throws CustomException;
  
  /**
   * Parse raw String data to rollNumber.
   *
   * @param columnNumber the column number
   * @return the int
   * @throws CustomException the custom exception
   */
  int parseColumnNumberForSorting(String columnNumber) throws CustomException;
  
  /**
   * Parse raw String data to rollNumber.
   *
   * @param sortingOrder the sorting order
   * @return the sorting order
   * @throws CustomException the custom exception
   */
  SortingOrder parseOrderOfSorting(String sortingOrder) throws CustomException;
}
