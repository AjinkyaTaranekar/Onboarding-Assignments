package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.exception.CustomException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
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
  public void validateFullName(final String name) throws CustomException {
    if (name.isEmpty()) {
      logger.error(ExceptionsConstantsUtils.INVALID_PARAMETER);
      throw new CustomException(ExceptionsConstantsUtils.INVALID_PARAMETER);
    }
  }
  
  @Override
  public void validateNumeric(final String number) throws CustomException {
    if (number.contains("-")) {
      logger.error(String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          number));
      throw new CustomException(String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          number));
    }
    if (number.contains(".")) {
      logger.error(String.format(ExceptionsConstantsUtils.DECIMAL_PARAMETER,
          number));
      throw new CustomException(String.format(ExceptionsConstantsUtils.DECIMAL_PARAMETER,
          number));
    }
    if (!StringUtils.isNumeric(number)) {
      logger.error(String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          number));
      throw new CustomException(String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          number));
    }
  }
  
  @Override
  public void validateCourses(final String... coursesList) throws CustomException {
    final Set<String> coursesSet = new HashSet<>(List.of(coursesList));
    if (coursesSet.size() != coursesList.length) {
      logger.error(String.format(ExceptionsConstantsUtils.REPEATED_COURSE_FOUND,
          Arrays.toString(coursesList)));
      throw new CustomException(String.format(ExceptionsConstantsUtils.REPEATED_COURSE_FOUND,
          Arrays.toString(coursesList)));
    }
    if (coursesSet.size() != StringConstantsUtils.MUST_COURSE_COUNT) {
      logger.error(ExceptionsConstantsUtils.INVALID_COURSE_COUNT);
      throw new CustomException(ExceptionsConstantsUtils.INVALID_COURSE_COUNT);
    }
    final Set<String> coursesEnumSet = new HashSet<>();
    for (final Courses course : Courses.values()) {
      coursesEnumSet.add(course.toString());
    }
    
    for (final String course : coursesSet) {
      if (!coursesEnumSet.contains(course)) {
        logger.error(String.format(ExceptionsConstantsUtils.INVALID_COURSE,
            course));
        throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_COURSE,
            course));
      }
    }
  }
  
  @Override
  public void validateColumnNumberForSorting(final int columnNumber) throws CustomException {
    if (columnNumber < 0 || columnNumber > 4) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER,
          columnNumber));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER,
          columnNumber));
    }
  }
  
  @Override
  public void checkDataIsNull(String data, String checkedOnAttribute) throws CustomException {
    if (data == null) {
      throw new CustomException(
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL, checkedOnAttribute));
    }
  }
}
