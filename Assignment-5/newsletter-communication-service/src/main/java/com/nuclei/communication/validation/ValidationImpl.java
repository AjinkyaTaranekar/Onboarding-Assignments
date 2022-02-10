package com.nuclei.communication.validation;

import com.nuclei.communication.constants.ResponseConstantsUtils;
import com.nuclei.communication.constants.RegexConstantsUtils;
import com.nuclei.communication.constants.StringConstantsUtils;
import com.nuclei.communication.exceptions.ValidationException;

import java.util.Objects;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * The type Validation.
 */
@Component
public class ValidationImpl implements Validation {
  
  /**
   * Validate string.
   *
   * @param string the string
   *
   * @throws ValidationException the validation exception
   */
  private void validateString(String string) throws ValidationException {
    if (StringUtils.isBlank(string)) {
      throw new ValidationException(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
          HttpStatus.BAD_REQUEST);
    }
  }
  
  /**
   * Validate integer.
   *
   * @param number the number
   *
   * @throws ValidationException the validation exception
   */
  private void validateInteger(Integer number) throws ValidationException {
    if (Objects.isNull(number)) {
      throw new ValidationException(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
          HttpStatus.BAD_REQUEST);
    }
    if (number <= 0) {
      throw new ValidationException(ResponseConstantsUtils.NEGATIVE_OR_ZERO_PARAMETER,
          HttpStatus.BAD_REQUEST);
    }
  }
  
  /**
   * Validate name.
   *
   * @param name the name
   *
   * @throws ValidationException the validation exception
   */
  @Override
  public void validateName(final String name) throws ValidationException {
    try {
      validateString(name);
    } catch (ValidationException exception) {
      throw new ValidationException(String.format(exception.getMessage(),
          StringConstantsUtils.NAME),
          exception.getStatusCode(), exception);
    }
  }
  
  /**
   * Validate email id.
   *
   * @param email the email
   *
   * @throws ValidationException the validation exception
   */
  @Override
  public void validateEmailId(final String email) throws ValidationException {
    try {
      validateString(email);
    } catch (ValidationException exception) {
      throw new ValidationException(String.format(exception.getMessage(),
          StringConstantsUtils.EMAIL), exception.getStatusCode(), exception);
    }
    final Matcher matcher =
        RegexConstantsUtils.VALID_EMAIL_ID_REGEX.matcher(email);
    if (!matcher.find()) {
      throw new ValidationException(String.format(ResponseConstantsUtils.INVALID_PARAMETER,
          StringConstantsUtils.EMAIL, email),
          HttpStatus.BAD_REQUEST);
    }
  }
  
}
