package com.nuclei.assignment.service.inputvalidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.exception.CustomException;
import org.junit.jupiter.api.Test;


public class InputValidationTest {
  /**
   * validate name when input is null.
   */
  @Test
  public void validateNameWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateString(input));
    
    final String expectedMessage = ExceptionsConstantsUtils.EMPTY_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate age when input is null.
   */
  @Test
  public void  validateNumericWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateNumeric(input));
  
    final String expectedMessage = ExceptionsConstantsUtils.EMPTY_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate name when input is null.
   */
  @Test
  public void validateNameWhenInputIsEmptyString() {
    final String input = "";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateString(input));
  
    final String expectedMessage = ExceptionsConstantsUtils.EMPTY_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate age when input is null.
   */
  @Test
  public void  validateNumericWhenInputIsEmptyString() {
    final String input = "";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateNumeric(input));
  
    final String expectedMessage = ExceptionsConstantsUtils.EMPTY_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate name when input is null.
   */
  @Test
  public void validateNumericWhenInputIsNegative() {
    final String input = "-1";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateNumeric(input));
  
    final String expectedMessage = ExceptionsConstantsUtils.NEGATIVE_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate name when input is null.
   */
  @Test
  public void validateNumericWhenInputIsDecimal() {
    final String input = "1.5";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateNumeric(input));
  
    final String expectedMessage = ExceptionsConstantsUtils.DECIMAL_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate name when input is null.
   */
  @Test
  public void validateNumericWhenInputIsCharacter() {
    final String input = "abc";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputValidationImpl().validateNumeric(input));
  
    final String expectedMessage = ExceptionsConstantsUtils.CHARACTER_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
}