package com.nuclei.assignment.constants;

/**
 * The type Exceptions constants utils.
 */
public final class ExceptionsConstantsUtils {
  
  /**
   * The constant NO_INPUT.
   */
  public static final String NO_INPUT = "No Input Given";
  /**
   * The constant INVALID_INPUT.
   */
  public static final String INVALID_INPUT = "Invalid Input Entry %s";
  /**
   * The constant INVALID_NAME.
   */
  public static final String INVALID_NAME = "Empty User Name";
  /**
   * The constant INVALID_ID.
   */
  public static final String INVALID_ID = "Invalid User ID : %s";
  
  /**
   * The constant INVALID_PARAMETER.
   */
  public static final String INVALID_PARAMETER = "Empty Parameter.";
  
  /**
   * The constant CHARACTER_PARAMETER.
   */
  public static final String CHARACTER_PARAMETER =
      "The parameter can't be character: %s";
  
  /**
   * The constant DECIMAL_PARAMETER.
   */
  public static final String DECIMAL_PARAMETER =
      "The parameter can't be decimal: %s";
  
  /**
   * The constant NEGATIVE_PARAMETER.
   */
  public static final String NEGATIVE_PARAMETER =
      "The parameter can't be negative or zero: %s";
  
  /**
   * The constant DATA_IS_NULL.
   */
  public static final String DATA_IS_NULL =
      "Data for %s found null";
  
  /**
   * The constant DETAILS_NOT_FOUND.
   */
  public static final String DETAILS_NOT_FOUND =
      "Cannot found key and value here: %s";
  
  /**
   * The constant MUST_HAVE_KEY_VALUE_COUNT.
   */
  public static final int MUST_HAVE_KEY_VALUE_COUNT = 2;
  /**
   * The constant MUST_HAVE_USERS_BEFORE_DEPENDENCY_FORMATION.
   */
  public static final int MUST_HAVE_USERS_BEFORE_DEPENDENCY_FORMATION = 2;
  
  /**
   * The constant ID_ALREADY_EXISTS.
   */
  public static final String ID_ALREADY_EXISTS = "ID %s Already exists";
  /**
   * The constant PARENT_NOT_EQUALS_CHILD.
   */
  public static final String PARENT_NOT_EQUALS_CHILD =
      "Parent %s not equals child %s.";
  /**
   * The constant CYCLIC_DEPENDENCY.
   */
  public static final String CYCLIC_DEPENDENCY =
      "Cyclic Dependency between %s and %s";
  /**
   * The constant NO_DEPENDENCY.
   */
  public static final String NO_DEPENDENCY = "No Dependency between %s and %s";
  /**
   * The constant INVALID_PARENT_ID.
   */
  public static final String INVALID_PARENT_ID = "Invalid Parent ID %s";
  /**
   * The constant INVALID_CHILD_ID.
   */
  public static final String INVALID_CHILD_ID = "Invalid Child ID %s";
  
}
