package com.nuclei.assignment.service.inputparser;

import com.nuclei.assignment.exception.CustomException;

/**
 * The interface Input parser.
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
   * Parse raw String data to id.
   *
   * @param id the id
   * @return the int
   * @throws CustomException the custom exception
   */
  int parseId(String id) throws CustomException;
  
  /**
   * Parse raw String data to details.
   *
   * @param details the details
   * @return the String[]
   * @throws CustomException the custom exception
   */
  String[] parseDetails(String details) throws CustomException;
}
