package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.exception.CustomException;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Input Validation Implementation containing method to validate different
 * properties.
 */
public class InputValidationImpl implements InputValidation {
  
  /**
   * The logger.
   */
  private final Log logger = LogFactory.getLog(InputValidationImpl.class);
  
  @Override
  public void validateString(final String string) throws CustomException {
    if (StringUtils.isBlank(string)) {
      throw new CustomException(ExceptionsConstantsUtils.EMPTY_PARAMETER);
    }
  }
  
  @Override
  public void validateNumeric(final String number) throws CustomException {
    if (number.contains("-")) {
      throw new CustomException(ExceptionsConstantsUtils.NEGATIVE_PARAMETER);
    }
    if (number.contains(".")) {
      throw new CustomException(ExceptionsConstantsUtils.DECIMAL_PARAMETER);
    }
    if (!StringUtils.isNumeric(number)) {
      throw new CustomException(ExceptionsConstantsUtils.CHARACTER_PARAMETER);
    }
  }
  
  @Override
  public void validateDetails(final String... details) throws CustomException {
    if (details.length != ExceptionsConstantsUtils.MUST_HAVE_KEY_VALUE_COUNT) {
      logger.error(String.format(ExceptionsConstantsUtils.DETAILS_NOT_FOUND,
          Arrays.toString(details)));
      throw new CustomException(String.format(ExceptionsConstantsUtils.DETAILS_NOT_FOUND,
          Arrays.toString(details)));
    }
  }
}
