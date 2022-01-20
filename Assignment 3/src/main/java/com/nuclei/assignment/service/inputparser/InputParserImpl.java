package com.nuclei.assignment.service.inputparser;

import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.inputvalidation.InputValidation;
import com.nuclei.assignment.service.inputvalidation.InputValidationImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The type Input parser.
 */
public class InputParserImpl implements InputParser {
  
  /**
   * The Input validation.
   */
  private final InputValidation inputValidation;
  
  /**
   * The logger.
   */
  private final Log logger = LogFactory.getLog(InputParserImpl.class);
  
  /**
   * Instantiates a new Input parser.
   */
  public InputParserImpl() {
    inputValidation = new InputValidationImpl();
  }
  
  @Override
  public String parseName(final String name) throws CustomException {
    try {
      inputValidation.validateString(name);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.NAME),
          exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.NAME), exception);
    }
    return name;
  }
  
  @Override
  public int parseId(final String id) throws CustomException {
    try {
      inputValidation.validateString(id);
      inputValidation.validateNumeric(id);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.ID, id),
          exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.ID, id), exception);
    }
    return Integer.parseInt(id);
  }
  
  @Override
  public String[] parseDetails(String details) throws CustomException {
    try {
      inputValidation.validateString(details);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.USER_DETAILS),
          exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.USER_DETAILS), exception);
    }
    final String[] detailsList = details.split(",");
    inputValidation.validateDetails(detailsList);
    return detailsList;
  }
}
