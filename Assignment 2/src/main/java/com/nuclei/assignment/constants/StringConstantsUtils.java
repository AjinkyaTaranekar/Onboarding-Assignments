package com.nuclei.assignment.constants;

/**
 * Constant Strings used in code to provide user a brief
 * of what to do during input.
 */
public class StringConstantsUtils {
  
  /**
   * The constant MENU_DETAILS_INFO.
   */
  public static final String[] MENU_DETAILS_INFO = {
    "User Menu Details ",
    "1. Add User details.",
    "2. Display User details.",
    "3. Delete User details",
    "4. Save User details.",
    "5. Exit",
    "Please choose from (1-5):",
  };
  /**
   * The constant ADD_USER_DETAILS_INFO.
   */
  public static final String ADD_USER_DETAILS_INFO = "Add User Details ";
  
  /**
   * The constant ADD_USER_NAME.
   */
  public static final String ADD_USER_NAME = "Enter Full Name";
  /**
   * The constant ADD_USER_AGE.
   */
  public static final String ADD_USER_AGE = "Enter Age";
  /**
   * The constant ADD_USER_ADDRESS.
   */
  public static final String ADD_USER_ADDRESS = "Enter Address";
  /**
   * The constant ADD_USER_ROLL_NUMBER.
   */
  public static final String ADD_USER_ROLL_NUMBER = "Enter Roll Number";
  /**
   * The constant DELETE_USER_ROLL_NUMBER.
   */
  public static final String DELETE_USER_ROLL_NUMBER = "Enter Roll Number to "
      + "be deleted";
  /**
   * The constant ADD_USER_COURSES.
   */
  public static final String ADD_USER_COURSES = "Set of courses interested to"
      + " enroll";
  /**
   * The constant COURSES_NOTE.
   */
  public static final String[] COURSES_NOTE = {
    "NOTE:",
    "1. " + StringConstantsUtils.MUST_COURSE_COUNT + "Courses are must.",
    "2. Enter Courses as comma separated example: A,B,C,D}",
    "3. Below is list of courses available."
  };
  /**
   * The constant LIST_FORMAT.
   */
  public static final String LIST_FORMAT = "%d. %s\n";
  /**
   * The constant MUST_COURSE_COUNT.
   */
  public static final int MUST_COURSE_COUNT = 4;
  /**
   * The constant CONTINUE_MORE_OPERATIONS.
   */
  public static final String CONTINUE_MORE_OPERATIONS
      = "Continue more operations (y/n):";
  /**
   * The constant SAVE_USERS.
   */
  public static final String SAVE_USERS
      = "Save user operations (y/n):";
  /**
   * The constant CONFIRMATION.
   */
  public static final String CONFIRMATION = "y";
  /**
   * The constant DIVIDER.
   */
  public static final String DIVIDER = "---------------------------------------"
      +
      "------------------------------------------------------------------------";
  /**
   * The constant LEFT_ALIGN_FORMAT.
   */
  public static final String LEFT_ALIGN_FORMAT = "| %-20s | %-11s | %-8s | "
      + "%-40s | %-16s |%n";
  /**
   * The constant SORT_BY_ORDER.
   */
  public static final String SORT_BY_ORDER = "Sort Order (ASC/DESC): ";
  /**
   * The constant SORT_BY_COLUMN.
   */
  public static final String SORT_BY_COLUMN = "Sort Column (Enter choice): ";
  /**
   * The constant USER_FIELDS.
   */
  public static final String[] USER_FIELDS = {
    "Name",
    "Roll Number",
    "Age",
    "Address",
    "Courses"
  };
  /**
   * The constant USER_STORAGE.
   */
  public static final String USER_STORAGE = "./user.ser";
}
