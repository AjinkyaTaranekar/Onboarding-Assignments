package com.nuclei.example.validation;

import com.nuclei.example.constants.ExceptionsConstantsUtils;
import com.nuclei.example.constants.StringConstantsUtils;
import com.nuclei.example.enums.InventoryType;
import com.nuclei.example.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Validation.
 */
public class ValidationImpl implements Validation {
  
  private void validateString (String string) throws ValidationException {
    if (StringUtils.isBlank(string)) {
      throw new ValidationException(ExceptionsConstantsUtils.EMPTY_OR_NULL_PARAMETER, ExceptionsConstantsUtils.BAD_REQUEST);
    }
  }
  
  private void validateInteger (Integer number) throws ValidationException {
    if (number < 0) {
      throw new ValidationException(ExceptionsConstantsUtils.NEGATIVE_PARAMETER, ExceptionsConstantsUtils.BAD_REQUEST);
    }
  }
  
  private void validateDouble (Double number) throws ValidationException {
    validateInteger(number.intValue());
  }
  
  
  @Override
  public void validateName (final String name) throws ValidationException {
    try {
      validateString(name);
    } catch (ValidationException exception) {
      throw new ValidationException(String.format(exception.getMessage(),
          StringConstantsUtils.NAME), exception.getStatusCode(), exception);
    }
  }
  
  @Override
  public void validateId (final Integer id) throws ValidationException {
    try {
      validateInteger(id);
    } catch (ValidationException exception) {
      throw new ValidationException(String.format(exception.getMessage(),
          StringConstantsUtils.ID, id), exception.getStatusCode(), exception);
    }
  }
  
  @Override
  public void validatePrice (final Double price) throws ValidationException {
    try {
      validateDouble(price);
    } catch (ValidationException exception) {
      throw new ValidationException(String.format(exception.getMessage(),
          StringConstantsUtils.PRICE, price), exception.getStatusCode(), exception);
    }
  }
  
  @Override
  public void validateQuantity (final Double quantity) throws ValidationException {
    try {
      validateDouble(quantity);
    } catch (ValidationException exception) {
      throw new ValidationException(String.format(exception.getMessage(),
          StringConstantsUtils.QUANTITY, quantity), exception.getStatusCode(), exception);
    }
  }
  
  @Override
  public void validateInventoryType (String type) throws ValidationException {
    if (!InventoryType.checkWhetherInventoryTypeIsPresent(type)) {
      throw new ValidationException(ExceptionsConstantsUtils.INVALID_TYPE,
          ExceptionsConstantsUtils.BAD_REQUEST);
    }
  }
}
