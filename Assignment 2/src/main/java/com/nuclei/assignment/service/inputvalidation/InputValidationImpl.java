package com.nuclei.assignment.service.inputvalidation;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.exception.CustomException;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
  public void validateString(final String string) throws CustomException {
    if (StringUtils.isBlank(string)) {
      throw new CustomException(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY);
    }
  }
  
  @Override
  public void validateNumeric(final String number) throws CustomException {
    if (number.contains("-")) {
      throw new CustomException(ExceptionsConstantsUtils.NEGATIVE_PARAMETER);
    }
    if (number.contains(".")) {
      throw new CustomException(ExceptionsConstantsUtils.DECIMAL_PARAMETER);
    }
    if (!StringUtils.isNumeric(number)) {
      throw new CustomException(ExceptionsConstantsUtils.CHARACTER_PARAMETER);
    }
  }
  
  @Override
  public void validateCourses(final String... coursesList) throws CustomException {
    final Set<String> coursesSet = new HashSet<>(List.of(coursesList));
    if (coursesSet.size() < StringConstantsUtils.MUST_COURSE_COUNT) {
      logger.error(ExceptionsConstantsUtils.INVALID_COURSE_COUNT);
      throw new CustomException(ExceptionsConstantsUtils.INVALID_COURSE_COUNT);
    }
    
    for (final String course : coursesSet) {
      Courses.checkWhetherCourseIsPresent(course);
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
  public void validateUser(UserEntity user) {
    Objects.requireNonNull(user, ExceptionsConstantsUtils.INVALID_ROLL_NUMBER);
  }
}
