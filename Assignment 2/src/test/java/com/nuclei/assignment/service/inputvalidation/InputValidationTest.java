package com.nuclei.assignment.service.inputvalidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;


/**
 * The type Input validation test.
 */
public class InputValidationTest {
  
  /**
   * Validate name when input is null.
   */
  @Test
  public void validateNameWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateName(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL, "Name");
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate address when input is null.
   */
  @Test
  public void  validateAddressWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateAddress(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL, "Address");
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate age when input is null.
   */
  @Test
  public void  validateAgeWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateAge(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL, "Age");
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate courses when input is null.
   */
  @Test
  public void  validateCoursesWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateCourses(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL, "Courses");
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate roll number when input is null.
   */
  @Test
  public void  validateRollNumberWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateRollNumber(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL, "Roll Number");
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate column number for sorting when input is null.
   */
  @Test
  public void  validateColumnNumberForSortingWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateColumnNumberForSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL, "Column Number");
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate order of sorting when input is null.
   */
  @Test
  public void  validateOrderOfSortingWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateOrderOfSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL, "Sorting Order");
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate name when input is empty.
   */
  @Test
  public void validateNameWhenInputIsEmpty() {
    final String input = "";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateName(input));
    
    final String expectedMessage = ExceptionsConstantsUtils.INVALID_NAME;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate address when input is empty.
   */
  @Test
  public void  validateAddressWhenInputIsEmpty() {
    final String input = "";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateAddress(input));
    
    final String expectedMessage = ExceptionsConstantsUtils.INVALID_ADDRESS;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate age when input is negative.
   */
  @Test
  public void  validateAgeWhenInputIsNegative() {
    final String input = "-21";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateAge(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_AGE, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate age when input is character.
   */
  @Test
  public void  validateAgeWhenInputIsCharacter() {
    final String input = "abc";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateAge(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_AGE, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate courses when input has repeated courses.
   */
  @Test
  public void  validateCoursesWhenInputHasRepeatedCourses() {
    final String input = "A,B,C,A,D";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateCourses(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.REPEATED_COURSE_FOUND, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate courses when input has false courses.
   */
  @Test
  public void  validateCoursesWhenInputHasFalseCourses() {
    final String input = "A,B,C,G,M";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateCourses(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_COURSE, "G");
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate courses when input has less courses.
   */
  @Test
  public void  validateCoursesWhenInputHasLessCourses() {
    final String input = "A,B,C";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateCourses(input));
    
    final String expectedMessage =
        ExceptionsConstantsUtils.INVALID_COURSE_COUNT;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate roll number when input is negative.
   */
  @Test
  public void  validateRollNumberWhenInputIsNegative() {
    final String input = "-21";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateRollNumber(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_ROLL_NUMBER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate roll number when input is character.
   */
  @Test
  public void  validateRollNumberWhenInputIsCharacter() {
    final String input = "abc";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateRollNumber(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_ROLL_NUMBER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate column number for sorting when input is not in range.
   */
  @Test
  public void  validateColumnNumberForSortingWhenInputIsNotInRange() {
    final String input = "5";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateColumnNumberForSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate column number for sorting when input is character.
   */
  @Test
  public void  validateColumnNumberForSortingWhenInputIsCharacter() {
    final String input = "abc";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateColumnNumberForSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate order of sorting when input is not found.
   */
  @Test
  public void  validateOrderOfSortingWhenInputIsNotFound() {
    final String input = "ascend";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateOrderOfSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_SORTING_ORDER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate name.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void validateName() throws CustomException {
    final String input = "Ajinkya";
    final String expectedMessage = "Ajinkya";
    final String actualMessage =
        new InputValidationImpl().validateName(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate address.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  validateAddress() throws CustomException {
    final String input = "85, Sch 114";
    final String expectedMessage = "85, Sch 114";
    final String actualMessage =
        new InputValidationImpl().validateAddress(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate age.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  validateAge() throws CustomException {
    final String input = "1";
    final int expectedMessage = 1;
    final int actualMessage =
        new InputValidationImpl().validateAge(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate courses.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  validateCourses() throws CustomException {
    final String input = "A,B,C,D";
    final Set<Courses> expectedMessage = new HashSet<>(
        Arrays.asList(Courses.A, Courses.B, Courses.C, Courses.D));
    final Set<Courses> actualMessage =
        new InputValidationImpl().validateCourses(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate roll number.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  validateRollNumber() throws CustomException {
    final String input = "1";
    final int expectedMessage = 1;
    final int actualMessage =
        new InputValidationImpl().validateRollNumber(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate column number for sorting.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  validateColumnNumberForSorting() throws CustomException {
    final String input = "1";
    final int expectedMessage = 1;
    final int actualMessage =
        new InputValidationImpl().validateColumnNumberForSorting(input);
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate order of sorting.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void validateOrderOfSorting() throws CustomException {
    final String input = "ASC";
    final SortingOrder actualMessage = 
        new InputValidationImpl().validateOrderOfSorting(input);
    final SortingOrder expectedMessage = SortingOrder.ASC;
    
    assertEquals(expectedMessage, actualMessage);
  }
  
}
