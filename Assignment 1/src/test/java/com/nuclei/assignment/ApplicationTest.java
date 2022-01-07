package com.nuclei.assignment;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nuclei.assignment.constants.ExceptionsConstants;
import com.nuclei.assignment.exception.CustomException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


/**
 * Testing whole Application.
 * **/
public class ApplicationTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;
  
  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;
  
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
  
  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }
  
  
  @Test
  public void addingItemWithoutNameFlag() {
    final String[] item = {"-price", "12", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
    
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_INPUT;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  @Test
  public void addingItemWithoutTypeFlag() {
    final String[] item = {"-name", "apple"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_INPUT;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithTwoFlagConsecutively() {
    final String[] item = {"-name", "-type", "apple", "RAW"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
    
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_INPUT;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  @Test
  public void addingItemWithMultipleNameEntries() {
    final String[] item = {"-name", "apple", "-name", "mango"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_INPUT;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithEmptyNameEntry() {
    final String[] item = {"-name", "", "-type", "RAW"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_NAME;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithNegativePrice() {
    final String[] item = {"-name", "apple", "-price", "-12", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_PRICE;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithCharacterPrice() {
    final String[] item = {"-name", "apple", "-price", "abc", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_PRICE;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithNegativeQuantity() {
    final String[] item = {"-name", "apple", "-quantity", "-12", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_QUANTITY;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithCharacterQuantity() {
    final String[] item = {"-name", "apple", "-quantity", "ab2", "-type", "raw"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_QUANTITY;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithWrongType() {
    final String[] item = {"-name", "apple", "-type", "market"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_TYPE;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void checkTaxForRawItemWhenItemPriceIsNegative() {
    final String[] item = {"-name", "apple", "-type", "RAW", "-price",
      "-12"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = ExceptionsConstants.INVALID_PRICE;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void checkTaxForRawItemWhenItemPriceIsPositive() {
    final String[] item = {"-name", "apple", "-price", "12", "-type", "raw",
      "-quantity", "12"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "RAW Item 1 apple with price 12.00 and "
        + "quantity 12.00 at tax 1.50 => final price as 13.50";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  @Test
  public void checkTaxForManufacturedItemWhenItemPriceIsPositive() {
    final String[] item = {"-name", "apple", "-price", "12", "-type", "manufactured"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "MANUFACTURED Item 1 apple with price 12"
        + ".00 and quantity 0.00 at tax 1.77 => final price as 13.77";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositiveAndUnder100() {
    final String[] item = {"-name", "apple", "-price", "12", "-type", "imported"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "IMPORTED Item 1 apple with price 12.00 "
        + "and quantity 0.00 at tax 6.20 => final price as 18.20";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositiveAndAbove100() throws CustomException {
    final String[] item = {"-name", "apple", "-price", "100", "-type",
      "imported"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "IMPORTED Item 1 apple with price 100.00 "
        + "and quantity 0.00 at tax 20.00 => final price as 120.00";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositiveAndAbove1000()  {
    final String[] item = {"-name", "apple", "-price", "1000", "-type",
      "imported"};
    final String addMoreItems = "n";
    provideInput(addMoreItems);
  
    Application.main(item);
    final String expectedMessage = "IMPORTED Item 1 apple with price 1000.00 "
        + "and quantity 0.00 at tax 155.00 => final price as 1155.00";
    assertTrue(getOutput().contains(expectedMessage));
  }
}