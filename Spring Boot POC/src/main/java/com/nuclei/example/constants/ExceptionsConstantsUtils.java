package com.nuclei.example.constants;

import com.nuclei.example.enums.InventoryType;

import java.util.Arrays;

/**
 * The type Exceptions constants utils.
 */
public final class ExceptionsConstantsUtils {
  
  /**
   * The constant EMPTY_OR_NULL_PARAMETER.
   */
  public static final String EMPTY_OR_NULL_PARAMETER =
      "Given %s data is null or empty";
  
  /**
   * The constant NEGATIVE_PARAMETER.
   */
  public static final String NEGATIVE_PARAMETER =
      "Inventory %s couldn't be negative: %s";
  
  /**
   * The constant INVALID_TYPE.
   */
  public static final String INVALID_TYPE = "Invalid Inventory Type: %s \n"
      + "Use only " + Arrays.toString(InventoryType.values());
  
  /**
   * The constant ID_NOT_FOUND.
   */
  public static final String ID_NOT_FOUND =
      "Inventory id not found: %s";
  
  /**
   * The constant BAD_REQUEST.
   */
  public static final int BAD_REQUEST = 400;
  
  /**
   * The constant NOT_FOUND.
   */
  public static final int NOT_FOUND = 404;
  
}