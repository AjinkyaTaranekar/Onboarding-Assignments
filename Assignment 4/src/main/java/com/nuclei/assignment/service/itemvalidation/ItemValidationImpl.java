package com.nuclei.assignment.service.itemvalidation;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.exception.InputException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * The type Item validation.
 */
public class ItemValidationImpl implements ItemValidation {
  @Override
  public void validateString(final String string) throws InputException {
    if (StringUtils.isBlank(string)) {
      throw new InputException(ExceptionsConstantsUtils.EMPTY_OR_NULL_PARAMETER);
    }
  }
  
  @Override
  public void validateNumeric(final String number) throws InputException {
    validateString(number);
    if (number.contains("-")) {
      throw new InputException(ExceptionsConstantsUtils.NEGATIVE_PARAMETER);
    }
    if (!NumberUtils.isParsable(number)) {
      throw new InputException(ExceptionsConstantsUtils.CHARACTER_PARAMETER);
    }
  }
}
