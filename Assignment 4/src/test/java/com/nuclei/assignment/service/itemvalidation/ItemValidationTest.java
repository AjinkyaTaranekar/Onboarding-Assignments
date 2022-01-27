package com.nuclei.assignment.service.itemvalidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.exception.InputException;
import com.nuclei.assignment.service.ItemValidationImpl;
import org.junit.jupiter.api.Test;

/**
 * The type Item validation test.
 */
public class ItemValidationTest {
  
  /**
   * validate String when input is null.
   */
  @Test
  public void validateStringWhenItemIsNull() {
    final String input = null;
    final Exception exception = assertThrows(InputException.class,
        () -> new ItemValidationImpl().validateString(input));
    
    final String expectedMessage = ExceptionsConstantsUtils.EMPTY_OR_NULL_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate number when input is null.
   */
  @Test
  public void  validateNumericWhenItemIsNull() {
    final String input = null;
    final Exception exception = assertThrows(InputException.class,
        () -> new ItemValidationImpl().validateNumeric(input));
    
    final String expectedMessage = ExceptionsConstantsUtils.EMPTY_OR_NULL_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate String when input is null.
   */
  @Test
  public void validateStringWhenItemIsEmptyString() {
    final String input = "";
    final Exception exception = assertThrows(InputException.class,
        () -> new ItemValidationImpl().validateString(input));
    
    final String expectedMessage = ExceptionsConstantsUtils.EMPTY_OR_NULL_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate number when input is null.
   */
  @Test
  public void  validateNumericWhenItemIsEmptyString() {
    final String input = "";
    final Exception exception = assertThrows(InputException.class,
        () -> new ItemValidationImpl().validateNumeric(input));
    
    final String expectedMessage = ExceptionsConstantsUtils.EMPTY_OR_NULL_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate String when input is null.
   */
  @Test
  public void validateNumericWhenItemIsNegative() {
    final String input = "-1";
    final Exception exception = assertThrows(InputException.class,
        () -> new ItemValidationImpl().validateNumeric(input));
    
    final String expectedMessage = ExceptionsConstantsUtils.NEGATIVE_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * validate String when input is null.
   */
  @Test
  public void validateNumericWhenItemIsCharacter() {
    final String input = "abc";
    final Exception exception = assertThrows(InputException.class,
        () -> new ItemValidationImpl().validateNumeric(input));
    
    final String expectedMessage = ExceptionsConstantsUtils.CHARACTER_PARAMETER;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
}
