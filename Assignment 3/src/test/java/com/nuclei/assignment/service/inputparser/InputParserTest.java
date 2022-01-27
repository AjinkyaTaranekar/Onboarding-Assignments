package com.nuclei.assignment.service.inputparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.exception.CustomException;
import org.junit.jupiter.api.Test;


public class InputParserTest {
  /**
   * parse name when input is null.
   */
  @Test
  public void parseNameWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseString(input, StringConstantsUtils.NAME));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.EMPTY_PARAMETER,
          StringConstantsUtils.NAME);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse age when input is null.
   */
  @Test
  public void  parseIdWhenInputIsNull() {
    final String input = null;
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseId(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.EMPTY_PARAMETER,
          StringConstantsUtils.ID);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse name when input is null.
   */
  @Test
  public void parseNameWhenInputIsEmptyString() {
    final String input = "";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseString(input, StringConstantsUtils.NAME));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.EMPTY_PARAMETER,
          StringConstantsUtils.NAME);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse age when input is null.
   */
  @Test
  public void  parseIdWhenInputIsEmptyString() {
    final String input = "";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseId(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.EMPTY_PARAMETER,
          StringConstantsUtils.ID);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse name when input is null.
   */
  @Test
  public void parseIdWhenInputIsNegative() {
    final String input = "-1";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseId(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.ID, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse name when input is null.
   */
  @Test
  public void parseIdWhenInputIsDecimal() {
    final String input = "1.5";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseId(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DECIMAL_PARAMETER,
          StringConstantsUtils.ID, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse name when input is null.
   */
  @Test
  public void parseIdWhenInputIsCharacter() {
    final String input = "abc";
    final Exception exception = assertThrows(CustomException.class,
        () -> new InputParserImpl().parseId(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.ID, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  
  /**
   * parse name.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void parseString() throws CustomException {
    final String input = "Ajinkya";
    final String expectedMessage = "Ajinkya";
    final String actualMessage =
        new InputParserImpl().parseString(input, StringConstantsUtils.NAME);
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * parse name.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void parseId() throws CustomException {
    final String input = "1";
    final int expectedMessage = 1;
    final int actualMessage = new InputParserImpl().parseId(input);
    
    assertEquals(expectedMessage, actualMessage);
  }
  
}