package com.nuclei.assignment.constants;

import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;

import java.util.Arrays;

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
  public static final String INVALID_INPUT = "Invalid Input Entry";
  
  /**
   * The constant DATA_IS_EMPTY.
   */
  public static final String DATA_IS_NULL_OR_EMPTY =
      "Data for %s is null or empty";
  
  /**
   * The constant CHARACTER_PARAMETER.
   */
  public static final String CHARACTER_PARAMETER =
      "The %s can't be character: %s";
  
  /**
   * The constant DECIMAL_PARAMETER.
   */
  public static final String DECIMAL_PARAMETER =
      "The %s can't be decimal: %s";
  
  /**
   * The constant NEGATIVE_PARAMETER.
   */
  public static final String NEGATIVE_PARAMETER =
      "The %s can't be negative or zero: %s";
  
  /**
   * The constant INVALID_ROLL_NUMBER.
   */
  public static final String INVALID_ROLL_NUMBER =
      "Invalid User Roll Number: %s";
  
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
      "Add at least " + StringConstantsUtils.MUST_COURSE_COUNT + " Courses";
  
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
  
}
