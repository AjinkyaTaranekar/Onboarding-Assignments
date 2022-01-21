package com.nuclei.assignment.service.inputparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;


/**
 * The type Input parser test.
 */
public class InputParserTest {
  
  /**
   * parse name when input is null.
   */
  @Test
  public void parseNameWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseName(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
          StringConstantsUtils.NAME);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse address when input is null.
   */
  @Test
  public void  parseAddressWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseAddress(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
          StringConstantsUtils.ADDRESS);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse age when input is null.
   */
  @Test
  public void  parseAgeWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseAge(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
          StringConstantsUtils.AGE);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse courses when input is null.
   */
  @Test
  public void  parseCoursesWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseCourses(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
          StringConstantsUtils.COURSES);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse roll number when input is null.
   */
  @Test
  public void  parseRollNumberWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseRollNumber(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
          StringConstantsUtils.ROLL_NUMBER);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse column number for sorting when input is null.
   */
  @Test
  public void  parseColumnNumberForSortingWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseColumnNumberForSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
          StringConstantsUtils.COLUMN_NUMBER);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse order of sorting when input is null.
   */
  @Test
  public void  parseOrderOfSortingWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseOrderOfSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
          StringConstantsUtils.SORTING_ORDER);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse name when input is empty.
   */
  @Test
  public void parseNameWhenInputIsEmpty() {
    final String input = "";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseName(input));
    
    final String expectedMessage = String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
        StringConstantsUtils.NAME);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse address when input is empty.
   */
  @Test
  public void  parseAddressWhenInputIsEmpty() {
    final String input = "";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseAddress(input));
    
    final String expectedMessage = String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
        StringConstantsUtils.ADDRESS);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse age when input is negative.
   */
  @Test
  public void  parseAgeWhenInputIsNegative() {
    final String input = "-21";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseAge(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.AGE, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse age when input is character.
   */
  @Test
  public void  parseAgeWhenInputIsCharacter() {
    final String input = "abc";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseAge(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.AGE, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse courses when input has false courses.
   */
  @Test
  public void  parseCoursesWhenInputHasFalseCourses() {
    final String input = "A,B,C,G,M";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseCourses(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_COURSE, "G");
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse courses when input has less courses.
   */
  @Test
  public void  parseCoursesWhenInputHasLessCourses() {
    final String input = "A,B,C";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseCourses(input));
    
    final String expectedMessage =
        ExceptionsConstantsUtils.INVALID_COURSE_COUNT;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse roll number when input is negative.
   */
  @Test
  public void  parseRollNumberWhenInputIsNegative() {
    final String input = "-21";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseRollNumber(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.ROLL_NUMBER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse roll number when input is character.
   */
  @Test
  public void  parseRollNumberWhenInputIsCharacter() {
    final String input = "abc";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseRollNumber(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.ROLL_NUMBER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse column number for sorting when input is not in range.
   */
  @Test
  public void  parseColumnNumberForSortingWhenInputIsNotInRange() {
    final String input = "5";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseColumnNumberForSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_COLUMN_NUMBER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse column number for sorting when input is character.
   */
  @Test
  public void  parseColumnNumberForSortingWhenInputIsCharacter() {
    final String input = "abc";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseColumnNumberForSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.COLUMN_NUMBER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse order of sorting when input is not found.
   */
  @Test
  public void  parseOrderOfSortingWhenInputIsNotFound() {
    final String input = "ascend";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseOrderOfSorting(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_SORTING_ORDER, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse name.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void parseName() throws CustomException {
    final String input = "Ajinkya";
    final String expectedMessage = "Ajinkya";
    final String actualMessage =
        new InputParserImpl().parseName(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse address.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  parseAddress() throws CustomException {
    final String input = "85, Sch 114";
    final String expectedMessage = "85, Sch 114";
    final String actualMessage =
        new InputParserImpl().parseAddress(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse age.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  parseAge() throws CustomException {
    final String input = "1";
    final int expectedMessage = 1;
    final int actualMessage =
        new InputParserImpl().parseAge(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse courses.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  parseCourses() throws CustomException {
    final String input = "A,B,C,D";
    final Set<Courses> expectedMessage = new HashSet<>(
        Arrays.asList(Courses.A, Courses.B, Courses.C, Courses.D));
    final Set<Courses> actualMessage =
        new InputParserImpl().parseCourses(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse roll number.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  parseRollNumber() throws CustomException {
    final String input = "1";
    final int expectedMessage = 1;
    final int actualMessage =
        new InputParserImpl().parseRollNumber(input);
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse column number for sorting.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void  parseColumnNumberForSorting() throws CustomException {
    final String input = "1";
    final int expectedMessage = 1;
    final int actualMessage =
        new InputParserImpl().parseColumnNumberForSorting(input);
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse order of sorting.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void parseOrderOfSorting() throws CustomException {
    final String input = "ASC";
    final SortingOrder actualMessage = 
        new InputParserImpl().parseOrderOfSorting(input);
    final SortingOrder expectedMessage = SortingOrder.ASC;
    
    assertEquals(expectedMessage, actualMessage);
  }
  
}
