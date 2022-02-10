package com.nuclei.iam.validation;

import com.nuclei.iam.exceptions.ValidationException;

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
  
  /**
   * Validate password.
   *
   * @param password the password
   *
   * @throws ValidationException the validation exception
   */
  void validatePassword(final String password) throws ValidationException;
  
  /**
   * Validate id.
   *
   * @param id the id
   *
   * @throws ValidationException the validation exception
   */
  void validateId(final Integer id) throws ValidationException;
  
  /**
   * Validate jwt.
   *
   * @param jwt the jwt
   *
   * @throws ValidationException the validation exception
   */
  void validateJwt(final String jwt) throws ValidationException;
  
}
