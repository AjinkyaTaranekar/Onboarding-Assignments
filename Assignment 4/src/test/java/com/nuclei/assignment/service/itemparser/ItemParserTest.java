package com.nuclei.assignment.service.itemparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.AttributeParseException;
import org.junit.jupiter.api.Test;


/**
 * Item Parser Tester containing various testcases while adding items.
 */
public class ItemParserTest {
  
  /**
   * Adding name with input.
   *
   * @throws AttributeParseException the attribute parse exception
   */
  @Test
  public void addingNameWithInput() throws AttributeParseException {
    String input = "apple";
    
    final String expectedMessage = "apple";
    final String actualMessage = new ItemParserImpl().parseName(input);
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Adding type with input.
   *
   * @throws AttributeParseException the attribute parse exception
   */
  @Test
  public void addingTypeWithInput() throws AttributeParseException {
    String input = "raw";
    
    final ItemType expectedMessage = ItemType.RAW;
    final ItemType actualMessage = new ItemParserImpl().parseType(input);
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Adding price with input.
   *
   * @throws AttributeParseException the attribute parse exception
   */
  @Test
  public void addingPriceWithInput() throws AttributeParseException {
    String input = "25.5";
    
    final double expectedMessage = 25.5;
    final double actualMessage = new ItemParserImpl().parsePrice(input);
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Adding quantity with input.
   *
   * @throws AttributeParseException the attribute parse exception
   */
  @Test
  public void addingQuantityWithInput() throws AttributeParseException {
    String input = "25.5";
    
    final double expectedMessage = 25.5;
    final double actualMessage = new ItemParserImpl().parseQuantity(input);
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Adding name with empty input.
   */
  @Test
  public void addingNameWithEmptyInput() {
    String input = "";
    final Exception exception = assertThrows(AttributeParseException.class,
        () -> new ItemParserImpl().parseName(input));
    
    final String expectedMessage = String.format(ExceptionsConstantsUtils.EMPTY_OR_NULL_PARAMETER,
        StringConstantsUtils.NAME);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  
  /**
   * Adding price with negative input.
   */
  @Test
  public void addingPriceWithNegativeInput() {
    String input = "-12";
    final Exception exception = assertThrows(AttributeParseException.class,
        () -> new ItemParserImpl().parsePrice(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.PRICE, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  
  /**
   * Adding price with character input.
   */
  @Test
  public void addingPriceWithCharacterInput() {
    String input = "abc";
    final Exception exception = assertThrows(AttributeParseException.class,
        () -> new ItemParserImpl().parsePrice(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.PRICE, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Adding quantity with negative input.
   */
  @Test
  public void addingQuantityWithNegativeInput() {
    String input = "-12";
    final Exception exception = assertThrows(AttributeParseException.class,
        () -> new ItemParserImpl().parseQuantity(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.QUANTITY, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  
  /**
   * Adding quantity with character input.
   */
  @Test
  public void addingQuantityWithCharacterInput() {
    String input = "abc";
    final Exception exception = assertThrows(AttributeParseException.class,
        () -> new ItemParserImpl().parseQuantity(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.QUANTITY, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  
  /**
   * Adding type with wrong input.
   */
  @Test
  public void addingTypeWithWrongInput() {
    String input = "-flag";
    final Exception exception = assertThrows(AttributeParseException.class,
        () -> new ItemParserImpl().parseType(input));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_TYPE, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  
}