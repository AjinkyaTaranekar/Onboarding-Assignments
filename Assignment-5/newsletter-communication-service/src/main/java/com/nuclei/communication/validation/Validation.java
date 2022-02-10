package com.nuclei.communication.validation;

import com.nuclei.communication.exceptions.ValidationException;

/**
 * The interface Validation.
 */
public interface Validation {
  /**
   * Validate name.
   *
   * @param name the name
   *
   * @throws ValidationException the validation exception
   */
  void validateName(String name) throws ValidationException;
  
  /**
   * Validate email id.
   *
   * @param emailId the email id
   *
   * @throws ValidationException the validation exception
   */
  void validateEmailId(final String emailId) throws ValidationException;
  
  
}
