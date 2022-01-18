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
  public void validateFullName(final String name) throws CustomException {
    if (name.isEmpty()) {
      logger.error(ExceptionsConstantsUtils.INVALID_PARAMETER);
      throw new CustomException(ExceptionsConstantsUtils.INVALID_PARAMETER);
    }
  }
  
  @Override
  public void validateNumeric(final String number) throws CustomException {
    if (number.contains("-")) {
      logger.error(String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          number));
      throw new CustomException(String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          number));
    }
    if (number.contains(".")) {
      logger.error(String.format(ExceptionsConstantsUtils.DECIMAL_PARAMETER,
          number));
      throw new CustomException(String.format(ExceptionsConstantsUtils.DECIMAL_PARAMETER,
          number));
    }
    if (!StringUtils.isNumeric(number)) {
      logger.error(String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          number));
      throw new CustomException(String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          number));
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
  
  @Override
  public void checkDataIsNull(String data, String checkedOnAttribute) throws CustomException {
    if (data == null) {
      throw new CustomException(
          String.format(ExceptionsConstantsUtils.DATA_IS_NULL, checkedOnAttribute));
    }
  }
}
