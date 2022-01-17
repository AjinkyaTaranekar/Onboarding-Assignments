package com.nuclei.assignment.service.inputparser;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
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
    inputValidation.checkDataIsNull(name, "Name");
    inputValidation.validateFullName(name);
    return name;
  }
  
  @Override
  public String parseAddress(final String address) throws CustomException {
    inputValidation.checkDataIsNull(address, "Address");
    inputValidation.validateFullName(address);
    return address;
  }
  
  @Override
  public int parseAge(final String age) throws CustomException {
    inputValidation.checkDataIsNull(age, "Age");
    inputValidation.validateNumeric(age);
    return Integer.parseInt(age);
  }
  
  @Override
  public Set<Courses> parseCourses(final String courses) throws CustomException {
    inputValidation.checkDataIsNull(courses, "Courses");
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
    inputValidation.checkDataIsNull(rollNumber, "Roll Number");
    inputValidation.validateNumeric(rollNumber);
    return Integer.parseInt(rollNumber);
  }
  
  @Override
  public int parseColumnNumberForSorting(final String columnNumber) throws CustomException {
    inputValidation.checkDataIsNull(columnNumber, "Column Number");
    inputValidation.validateNumeric(columnNumber);
    final int parsedColumnNumber = Integer.parseInt(columnNumber);
    inputValidation.validateColumnNumberForSorting(parsedColumnNumber);
    return parsedColumnNumber;
  }
  
  @Override
  public SortingOrder parseOrderOfSorting(final String sortingOrder) throws CustomException {
    inputValidation.checkDataIsNull(sortingOrder, "Sorting Order");
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
}
