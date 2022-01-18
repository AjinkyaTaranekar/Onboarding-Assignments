package com.nuclei.assignment.service.inputparser;

import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.inputvalidation.InputValidation;
import com.nuclei.assignment.service.inputvalidation.InputValidationImpl;

/**
 * The type Input parser.
 */
public class InputParserImpl implements InputParser {
  
  /**
   * The Input validation.
   */
  private final InputValidation inputValidation;
  
  /**
   * Instantiates a new Input parser.
   */
  public InputParserImpl() {
    inputValidation = new InputValidationImpl();
  }
  
  @Override
  public String parseName(final String name) throws CustomException {
    inputValidation.checkDataIsNull(name, "name");
    inputValidation.validateFullName(name);
    return name;
  }
  
  @Override
  public int parseId(final String id) throws CustomException {
    inputValidation.checkDataIsNull(id, "id");
    inputValidation.validateNumeric(id);
    return Integer.parseInt(id);
  }
  
  @Override
  public String[] parseDetails(String details) throws CustomException {
    inputValidation.checkDataIsNull(details, "details");
    final String[] detailsList = details.split(",");
    inputValidation.validateDetails(detailsList);
    return detailsList;
  }
}
