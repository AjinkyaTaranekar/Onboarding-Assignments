package com.nuclei.assignment;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testing whole Application.
 */
public class ApplicationTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;
  
  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;
  
  /**
   * Sets up output.
   */
  @BeforeEach
  public void setUpOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }
  
  private void provideInput(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }
  
  private String getOutput() {
    return testOut.toString();
  }
  
  /**
   * Restore system input output.
   */
  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }
  
  
  /**
   * Adding item without name flag.
   */
  @Test
  public void addingItemWithoutNameFlag() {
    final String[] item = {"-price", "12", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
    
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_INPUT_NAME_NOT_AT_START,
          Arrays.toString(item));
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  /**
   * Adding item without type flag.
   */
  @Test
  public void addingItemWithoutTypeFlag() {
    final String[] item = {"-name", "apple"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_INPUT_LENGTH,
          Arrays.toString(item));
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding item with two flag consecutively.
   */
  @Test
  public void addingItemWithTwoFlagConsecutively() {
    final String[] item = {"-name", "-type", "apple", "RAW"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
    
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_INPUT,
          "apple");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  /**
   * Adding item with multiple name entries.
   */
  @Test
  public void addingItemWithMultipleNameEntries() {
    final String[] item = {"-name", "apple", "-name", "mango"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
    
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_INPUT_NO_TYPE,
          Arrays.toString(item));
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding item with but tag has no data.
   */
  @Test
  public void addingItemButTagHasNoData() {
    final String[] item = {"-name", "apple", "-type", "raw", "-quantity"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
    
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_INPUT_NO_DATA_FOR_FLAG,
          Arrays.toString(item));
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding item but tag with wrong flag.
   */
  @Test
  public void addingItemButTagWithWrongFlag() {
    final String[] item = {"-name", "apple", "-type", "raw", "-cost", "120"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
    
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_INPUT, "-cost");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding item with empty name entry.
   */
  @Test
  public void addingItemWithEmptyNameEntry() {
    final String[] item = {"-name", "", "-type", "RAW"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = String.format(ExceptionsConstantsUtils.EMPTY_OR_NULL_PARAMETER,
        StringConstantsUtils.NAME);
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding Two items.
   */
  @Test
  public void addingTwoItems() {
    final String[] item1 = {"-name", "apple", "-price", "12", "-type", "raw"};
    final String[] item2 = {"-name", "mango", "-price", "12", "-type", "raw"};
    final String[] input = {
      "y",
      String.join(" ", item2),
      "n"
    };
    provideInput(String.join("\n", input));
    
    Application.main(item1);
    final String expectedMessage1 = "RAW Item apple with price 12.00 and "
        + "quantity 0.00 at tax 1.50 => final price as 13.50";
    assertTrue(getOutput().contains(expectedMessage1));
    final String expectedMessage2 = "RAW Item mango with price 12.00 and "
        + "quantity 0.00 at tax 1.50 => final price as 13.50";
    assertTrue(getOutput().contains(expectedMessage2));
  }
  
  /**
   * Adding item with negative price.
   */
  @Test
  public void addingItemWithNegativePrice() {
    final String[] item = {"-name", "apple", "-price", "-12", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
    
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.PRICE, -12);
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding item with character price.
   */
  @Test
  public void addingItemWithCharacterPrice() {
    final String[] item = {"-name", "apple", "-price", "abc", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.PRICE, "abc");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding item with negative quantity.
   */
  @Test
  public void addingItemWithNegativeQuantity() {
    final String[] item = {"-name", "apple", "-quantity", "-12", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.QUANTITY, -12);
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding item with character quantity.
   */
  @Test
  public void addingItemWithCharacterQuantity() {
    final String[] item = {"-name", "apple", "-quantity", "ab2", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.QUANTITY, "ab2");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding item with wrong type.
   */
  @Test
  public void addingItemWithWrongType() {
    final String[] item = {"-name", "apple", "-type", "market"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_TYPE, "market");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Check tax for raw item when item price is positive.
   */
  @Test
  public void checkTaxForRawItemWhenItemPriceIsPositive() {
    final String[] item = {"-name", "apple", "-price", "12", "-type", "raw",
      "-quantity", "12"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "RAW Item apple with price 12.00 and "
        + "quantity 12.00 at tax 1.50 => final price as 13.50";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  /**
   * Check tax for manufactured item when item price is positive.
   */
  @Test
  public void checkTaxForManufacturedItemWhenItemPriceIsPositive() {
    final String[] item = {"-name", "apple", "-price", "12", "-type", "manufactured"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "MANUFACTURED Item apple with price 12"
        + ".00 and quantity 0.00 at tax 1.77 => final price as 13.77";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  /**
   * Check tax for imported item when item price is positive and under 100.
   */
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositiveAndUnder100() {
    final String[] item = {"-name", "apple", "-price", "12", "-type", "imported"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "IMPORTED Item apple with price 12.00 "
        + "and quantity 0.00 at tax 6.20 => final price as 18.20";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Check tax for imported item when item price is positive and above 100.
   */
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositiveAndAbove100() {
    final String[] item = {"-name", "apple", "-price", "100", "-type",
      "imported"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "IMPORTED Item apple with price 100.00 "
        + "and quantity 0.00 at tax 20.00 => final price as 120.00";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Check tax for imported item when item price is positive and above 1000.
   */
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositiveAndAbove1000()  {
    final String[] item = {"-name", "apple", "-price", "1000", "-type",
      "imported"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "IMPORTED Item apple with price 1000"
        + ".00 and quantity 0.00 at tax 155.00 => final price as 1155.00";
    assertTrue(getOutput().contains(expectedMessage));
  }
}