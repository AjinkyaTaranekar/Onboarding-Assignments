package com.nuclei.assignment.constants;

import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;

import java.util.Arrays;

/**
 * Constant Exceptions used in code describing various situations
 * occurred during input.
 */
public final class ExceptionsConstantsUtils {
  
  /**
   * The constant NO_INPUT.
   */
  public static final String NO_INPUT = "No Input Given";
  
  /**
   * The constant INVALID_INPUT.
   */
  public static final String INVALID_INPUT = "Invalid Input Entry";
  
  /**
   * The constant INVALID_NAME.
   */
  public static final String INVALID_NAME = "Empty User Name.";
  
  /**
   * The constant INVALID_ADDRESS.
   */
  public static final String INVALID_ADDRESS = "Empty User Address.";
  
  /**
   * The constant CHARACTER_AGE.
   */
  public static final String CHARACTER_AGE = "User Age can't be character: %s";
  
  /**
   * The constant NEGATIVE_AGE.
   */
  public static final String NEGATIVE_AGE =
      "User Age can't be negative or zero: %s";
  
  /**
   * The constant INVALID_ROLL_NUMBER.
   */
  public static final String INVALID_ROLL_NUMBER =
      "Invalid User Roll Number: %s";
  
  /**
   * The constant CHARACTER_ROLL_NUMBER.
   */
  public static final String CHARACTER_ROLL_NUMBER =
      "User Roll Number can't be character: %s";
  
  /**
   * The constant NEGATIVE_ROLL_NUMBER.
   */
  public static final String NEGATIVE_ROLL_NUMBER =
      "User Roll Number can't be negative or zero: %s";
  
  /**
   * The constant ALREADY_PRESENT_ROLL_NUMBER.
   */
  public static final String ALREADY_PRESENT_ROLL_NUMBER =
      "User Roll Number %s already present";
  
  /**
   * The constant INVALID_COLUMN_NUMBER.
   */
  public static final String INVALID_COLUMN_NUMBER =
      "Invalid Column Number: %s";
  
  /**
   * The constant INVALID_SORTING_ORDER.
   */
  public static final String INVALID_SORTING_ORDER =
      "Invalid Sorting Order: %s\n Use only " + Arrays.toString(SortingOrder.values());
  
  /**
   * The constant INVALID_COURSE.
   */
  public static final String INVALID_COURSE =
      "Invalid User Course: %s\n Use only " + Arrays.toString(Courses.values());
  
  /**
   * The constant REPEATED_COURSE_FOUND.
   */
  public static final String REPEATED_COURSE_FOUND =
      "Repeated User Courses: %s";
  
  /**
   * The constant INVALID_COURSE_COUNT.
   */
  public static final String INVALID_COURSE_COUNT =
      "Add at most " + StringConstantsUtils.MUST_COURSE_COUNT + " Course";
  
  /**
   * The constant FAILED_TO_READ_STORAGE.
   */
  public static final String FAILED_TO_READ_STORAGE =
      "Failed to read " + StringConstantsUtils.USER_STORAGE;
  
  /**
   * The constant FAILED_TO_WRITE_STORAGE.
   */
  public static final String FAILED_TO_WRITE_STORAGE =
      "Failed to write " + StringConstantsUtils.USER_STORAGE;
  
  /**
   * The constant END_OF_FILE_REACHED.
   */
  public static final String END_OF_FILE_REACHED =
      "Failed to read " + StringConstantsUtils.USER_STORAGE;
  
  /**
   * The constant DATA_IS_NULL.
   */
  public static final String DATA_IS_NULL =
      "Data for %s found null";
  
}
