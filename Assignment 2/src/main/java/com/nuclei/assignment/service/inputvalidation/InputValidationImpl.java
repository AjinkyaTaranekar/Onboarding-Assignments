package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User Validation Implementation containing method to validate different properties.
 */
public class InputValidationImpl implements InputValidation {
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(InputValidationImpl.class);
  
  @Override
  public String validateName(final String name) throws CustomException {
    checkDataIsNull(name, "Name");
    if (name.isEmpty()) {
      logger.error(ExceptionsConstantsUtils.INVALID_NAME);
      throw new CustomException(ExceptionsConstantsUtils.INVALID_NAME);
    }
    return name;
  }
  
  @Override
  public String validateAddress(final String address) throws CustomException {
    checkDataIsNull(address, "Address");
    if (address.isEmpty()) {
      logger.error(ExceptionsConstantsUtils.INVALID_ADDRESS);
      throw new CustomException(ExceptionsConstantsUtils.INVALID_ADDRESS);
    }
    return address;
  }
  
  @Override
  public int validateAge(final String age) throws CustomException {
    checkDataIsNull(age, "Age");
    int parsedAge;
    try {
      parsedAge = Integer.parseInt(age);
      if (parsedAge <= 0) {
        logger.error(String.format(ExceptionsConstantsUtils.NEGATIVE_AGE,
            age));
        throw new CustomException(String.format(ExceptionsConstantsUtils.NEGATIVE_AGE,
            age));
      }
    } catch (NumberFormatException exception) {
      logger.error(String.format(ExceptionsConstantsUtils.CHARACTER_AGE,
          age));
      throw new CustomException(String.format(ExceptionsConstantsUtils.CHARACTER_AGE,
          age), exception);
    }
    return parsedAge;
  }
  
  @Override
  public Set<Courses> validateCourses(final String courses) throws CustomException {
    checkDataIsNull(courses, "Courses");
    final String[] coursesList = courses.split(",");
    if (coursesList.length < StringConstantsUtils.MUST_COURSE_COUNT) {
      logger.error(ExceptionsConstantsUtils.INVALID_COURSE_COUNT);
      throw new CustomException(ExceptionsConstantsUtils.INVALID_COURSE_COUNT);
    }

    final Set<Courses> validatedCourses = new HashSet<>();
    for (final String course : coursesList) {
      try {
        validatedCourses.add(Courses.valueOf(course.toUpperCase(Locale.ROOT)));
      } catch (IllegalArgumentException exception) {
        logger.error(String.format(ExceptionsConstantsUtils.INVALID_COURSE,
            course));
        throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_COURSE,
            course), exception);
      }
    }
    if (validatedCourses.size() < StringConstantsUtils.MUST_COURSE_COUNT
        || validatedCourses.size() != coursesList.length) {
      logger.error(String.format(ExceptionsConstantsUtils.REPEATED_COURSE_FOUND,
          courses));
      throw new CustomException(String.format(ExceptionsConstantsUtils.REPEATED_COURSE_FOUND,
          courses));
    }
    return validatedCourses;
  }
  
  @Override
  public int validateRollNumber(final String rollNumber) throws CustomException {
    checkDataIsNull(rollNumber, "Roll Number");
    int parsedRollNumber;
    try {
      parsedRollNumber = Integer.parseInt(rollNumber);
      if (parsedRollNumber <= 0) {
        logger.error(String.format(ExceptionsConstantsUtils.NEGATIVE_ROLL_NUMBER,
            rollNumber));
        throw new CustomException(String.format(ExceptionsConstantsUtils.NEGATIVE_ROLL_NUMBER,
            rollNumber));
      }
    } catch (NumberFormatException exception) {
      logger.error(String.format(ExceptionsConstantsUtils.CHARACTER_ROLL_NUMBER,
          rollNumber));
      throw new CustomException(String.format(ExceptionsConstantsUtils.CHARACTER_ROLL_NUMBER,
          rollNumber), exception);
    }
    return parsedRollNumber;
  }
  
  @Override
  public int validateColumnNumberForSorting(final String columnNumber) throws CustomException {
    checkDataIsNull(columnNumber, "Column Number");
    int parsedColumnNumber;
    try {
      parsedColumnNumber = Integer.parseInt(columnNumber);
      if (parsedColumnNumber < 0 || parsedColumnNumber > 4) {
        logger.error(String.format(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER,
            columnNumber));
        throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER,
            columnNumber));
      }
    } catch (NumberFormatException exception) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER,
          columnNumber));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER,
          columnNumber), exception);
    }
    return parsedColumnNumber;
  }
  
  @Override
  public SortingOrder validateOrderOfSorting(final String sortingOrder) throws CustomException {
    checkDataIsNull(sortingOrder, "Sorting Order");
    SortingOrder parsedSortingOrder;
    try {
      parsedSortingOrder = SortingOrder.valueOf(sortingOrder.toUpperCase(Locale.ROOT));
    } catch (IllegalArgumentException exception) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_SORTING_ORDER,
          sortingOrder));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_SORTING_ORDER,
          sortingOrder), exception);
    }
    return parsedSortingOrder;
  }
  
  private void checkDataIsNull(String data, String checkedOnAttribute) throws CustomException {
    if (data == null) {
      throw new CustomException(
          String.format(ExceptionsConstantsUtils.DATA_IS_NULL, checkedOnAttribute));
    }
  }
}
