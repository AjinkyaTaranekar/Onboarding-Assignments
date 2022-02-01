package com.nuclei.example.validation;

import com.nuclei.example.exceptions.ValidationException;

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
  void validateName (String name) throws ValidationException;
  
  /**
   * Validate id.
   *
   * @param id the id
   *
   * @throws ValidationException the validation exception
   */
  void validateId (String id) throws ValidationException;
  
  /**
   * Validate price.
   *
   * @param price the price
   *
   * @throws ValidationException the validation exception
   */
  void validatePrice (Double price) throws ValidationException;
  
  /**
   * Validate quantity.
   *
   * @param quantity the quantity
   *
   * @throws ValidationException the validation exception
   */
  void validateQuantity (Double quantity) throws ValidationException;
  
  /**
   * Validate inventory type.
   *
   * @param type the type
   *
   * @throws ValidationException the validation exception
   */
  void validateInventoryType (String type) throws ValidationException;
}
