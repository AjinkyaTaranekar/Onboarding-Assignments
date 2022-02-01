package com.nuclei.assignment.constants;

import com.nuclei.assignment.enums.ItemType;

import java.util.Arrays;

/**
 * Constant Exceptions used in code describing various situations
 * occurred during input.
 */
public final class ExceptionsConstantsUtils {
  
  /**
   * The constant INVALID_INPUT.
   */
  public static final String INVALID_INPUT = "Invalid Input Entry: %s";
  
  /**
   * The constant INVALID_INPUT_LENGTH.
   */
  public static final String INVALID_INPUT_LENGTH =
      "Length of input must be 4.: %s";
  
  /**
   * The constant INVALID_INPUT_NAME_NOT_AT_START.
   */
  public static final String INVALID_INPUT_NAME_NOT_AT_START =
      "-name flag must be start of input.: %s";
  
  /**
   * The constant INVALID_INPUT_NO_TYPE.
   */
  public static final String INVALID_INPUT_NO_TYPE =
      "item must have type: %s";
  
  /**
   * The constant INVALID_INPUT_NO_DATA_FOR_FLAG.
   */
  public static final String INVALID_INPUT_NO_DATA_FOR_FLAG =
      "Please, check no data present for some flag in : %s";
  
  /**
   * The constant INVALID_INPUT_DATA_NOT_NULL.
   */
  public static final String EMPTY_OR_NULL_PARAMETER =
      "Given %s data is null or empty";
  
  /**
   * The constant INVALID_QUANTITY.
   */
  public static final String CHARACTER_PARAMETER =
      "Item %s couldn't be character: %s";
  
  /**
   * The constant NEGATIVE_QUANTITY.
   */
  public static final String NEGATIVE_PARAMETER =
      "Item %s couldn't be negative: %s";
  
  /**
   * The constant INVALID_TYPE.
   */
  public static final String INVALID_TYPE = "Invalid Item Type: %s \n"
      + "Use only " + Arrays.toString(ItemType.values());
}
