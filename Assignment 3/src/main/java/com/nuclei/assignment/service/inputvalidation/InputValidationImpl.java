package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.exception.CustomException;

import org.apache.commons.lang3.StringUtils;

/**
 * Input Validation Implementation containing method to validate different
 * properties.
 */
public class InputValidationImpl implements InputValidation {
  
  @Override
  public void validateString(final String string) throws CustomException {
    if (StringUtils.isBlank(string)) {
      throw new CustomException(ExceptionsConstantsUtils.EMPTY_PARAMETER);
    }
  }
  
  @Override
  public void validateNumeric(final String number) throws CustomException {
    validateString(number);
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
}
