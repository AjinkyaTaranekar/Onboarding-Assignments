package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * User Validation Implementation containing method to validate different properties.
 */
public class InputValidationImpl implements InputValidation {
  
  @Override
  public String validateName(final String name) throws CustomException {
    if (name.isEmpty()) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_NAME);
    }
    return name;
  }
  
  @Override
  public String validateAddress(final String address) throws CustomException {
    if (address.isEmpty()) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_ADDRESS);
    }
    return address;
  }
  
  @Override
  public int validateAge(final String age) throws CustomException {
    int parsedAge;
    try {
      parsedAge = Integer.parseInt(age);
      if (parsedAge < 0) {
        throw new CustomException(ExceptionsConstantsUtils.INVALID_AGE);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_AGE,
        exception);
    }
    return parsedAge;
  }
  
  @Override
  public Set<Courses> validateCourses(final String courses) throws CustomException {
    final Set<Courses> validatedCourses = new HashSet<>();
    try {
      final String[] coursesList = courses.split(",");
      if (coursesList.length < StringConstantsUtils.MUST_COURSE_COUNT) {
        throw new CustomException(ExceptionsConstantsUtils.INVALID_COURSE_COUNT);
      }
      for (final String course : coursesList) {
        validatedCourses.add(Courses.valueOf(course.toUpperCase(Locale.ROOT)));
      }
      if (validatedCourses.size() < StringConstantsUtils.MUST_COURSE_COUNT) {
        throw new CustomException(ExceptionsConstantsUtils.INVALID_COURSE_COUNT);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_COURSE, exception);
    }
    return validatedCourses;
  }
  
  @Override
  public int validateRollNumber(final String rollNumber) throws CustomException {
    int parsedRollNumber;
    try {
      parsedRollNumber = Integer.parseInt(rollNumber);
      if (parsedRollNumber < 0) {
        throw new CustomException(ExceptionsConstantsUtils.INVALID_ROLL_NUMBER);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_ROLL_NUMBER,
        exception);
    }
    return parsedRollNumber;
  }
  
  @Override
  public int validateColumnNumberForSorting(final String columnNumber) throws CustomException {
    int parsedColumnNumber;
    try {
      parsedColumnNumber = Integer.parseInt(columnNumber);
      if (parsedColumnNumber < 0 || parsedColumnNumber > 4) {
        throw new CustomException(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER,
        exception);
    }
    return parsedColumnNumber;
  }
  
  @Override
  public SortingOrder validateOrderOfSorting(final String sortingOrder) throws CustomException {
    SortingOrder parsedSortingOrder;
    try {
      parsedSortingOrder = SortingOrder.valueOf(sortingOrder.toUpperCase(Locale.ROOT));
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_SORTING_ORDER,
        exception);
    }
    return parsedSortingOrder;
  }
}
