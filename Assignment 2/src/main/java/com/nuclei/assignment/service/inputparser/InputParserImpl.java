package com.nuclei.assignment.service.inputparser;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.inputvalidation.InputValidation;
import com.nuclei.assignment.service.inputvalidation.InputValidationImpl;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User Parse Implementation containing method to parse different properties.
 */
public class InputParserImpl implements InputParser {
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(InputParserImpl.class);
  
  /**
   * The Input validation.
   */
  private final InputValidation inputValidation;
  
  /**
   * Instantiates a new Input parser.
   */
  public InputParserImpl() {
    inputValidation = new InputValidationImpl();
  }
  
  @Override
  public String parseName(final String name) throws CustomException {
    try {
      inputValidation.validateString(name);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.NAME),
          exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.NAME), exception);
    }
    return name;
  }
  
  @Override
  public String parseAddress(final String address) throws CustomException {
    try {
      inputValidation.validateString(address);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.ADDRESS),
          exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.ADDRESS), exception);
    }
    return address;
  }
  
  @Override
  public int parseAge(final String age) throws CustomException {
    try {
      inputValidation.validateString(age);
      inputValidation.validateNumeric(age);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.AGE, age),
          exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.AGE, age), exception);
    }
    return Integer.parseInt(age);
  }
  
  @Override
  public Set<Courses> parseCourses(final String courses) throws CustomException {
    try {
      inputValidation.validateString(courses);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.COURSES),
          exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.COURSES), exception);
    }
    final String[] coursesList = courses.toUpperCase(Locale.ROOT).split(",");
    inputValidation.validateCourses(coursesList);
    final Set<Courses> parsedCourses = new HashSet<>();
    for (final String course : coursesList) {
      parsedCourses.add(Courses.valueOf(course));
    }
    return parsedCourses;
  }
  
  @Override
  public int parseRollNumber(final String rollNumber) throws CustomException {
    try {
      inputValidation.validateString(rollNumber);
      inputValidation.validateNumeric(rollNumber);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.ROLL_NUMBER,
          rollNumber), exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.ROLL_NUMBER, rollNumber), exception);
    }
    return Integer.parseInt(rollNumber);
  }
  
  @Override
  public int parseColumnNumberForSorting(final String columnNumber) throws CustomException {
    try {
      inputValidation.validateString(columnNumber);
      inputValidation.validateNumeric(columnNumber);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(),
          StringConstantsUtils.COLUMN_NUMBER, columnNumber), exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.COLUMN_NUMBER, columnNumber), exception);
    }
    final int parsedColumnNumber = Integer.parseInt(columnNumber);
    inputValidation.validateColumnNumberForSorting(parsedColumnNumber);
    return parsedColumnNumber;
  }
  
  @Override
  public SortingOrder parseOrderOfSorting(final String sortingOrder) throws CustomException {
    try {
      inputValidation.validateString(sortingOrder);
    } catch (CustomException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.SORTING_ORDER),
          exception);
      throw new CustomException(String.format(exception.getMessage(),
          StringConstantsUtils.SORTING_ORDER), exception);
    }
    SortingOrder parsedSortingOrder;
    try {
      parsedSortingOrder = SortingOrder.valueOf(sortingOrder.toUpperCase(Locale.ROOT));
    } catch (IllegalArgumentException exception) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_SORTING_ORDER,
          sortingOrder), exception);
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_SORTING_ORDER,
          sortingOrder), exception);
    }
    return parsedSortingOrder;
  }
}
