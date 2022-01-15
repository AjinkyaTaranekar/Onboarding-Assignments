package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.exception.CustomException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User Validation Implementation containing method to validate different properties.
 */
public class InputValidationImpl implements InputValidation {
  
  /**
   * The logger.
   */
  private final Log logger = LogFactory.getLog(InputValidationImpl.class);
  
  @Override
  public String validateName(final String name) throws CustomException {
    if (name.isEmpty()) {
      logger.error(ExceptionsConstantsUtils.INVALID_NAME);
      throw new CustomException(ExceptionsConstantsUtils.INVALID_NAME);
    }
    return name;
  }

  @Override
  public int validateId(final String id) throws CustomException {
    final int parsedId;
    try {
      parsedId = Integer.parseInt(id);
      if (parsedId < 0) {
        logger.error(String.format(ExceptionsConstantsUtils.NEGATIVE_ID, id));
        throw new CustomException(String.format(ExceptionsConstantsUtils.NEGATIVE_ID, id));
      }
    } catch (NumberFormatException exception) {
      logger.error(String.format(ExceptionsConstantsUtils.CHARACTER_ID, id));
      throw new CustomException(String.format(ExceptionsConstantsUtils.CHARACTER_ID,
          id), exception);
    }
    return parsedId;
  }
}
