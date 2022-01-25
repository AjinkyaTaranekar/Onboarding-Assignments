package com.nuclei.assignment.service.inputparser;

import com.nuclei.assignment.exception.CustomException;

/**
 * The interface Input parser.
 */
public interface InputParser {
  
  /**
   * Parse raw String data to string.
   * @param string               the string
   * @param checkedOnAttribute the checked on attribute
   * @return the string
   * @throws CustomException the custom exception
   */
  String parseString(String string, final String checkedOnAttribute) throws CustomException;
  
  /**
   * Parse raw String data to id.
   *
   * @param id the id
   * @return the int
   * @throws CustomException the custom exception
   */
  int parseId(String id) throws CustomException;
}
