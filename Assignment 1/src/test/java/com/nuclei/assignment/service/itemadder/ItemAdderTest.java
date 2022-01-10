package com.nuclei.assignment.service.itemadder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nuclei.assignment.constants.ExceptionsConstants;
import com.nuclei.assignment.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Item Adder Tester containing various testcases while adding items.
 * **/
public class ItemAdderTest {
  public ItemAdderTest() {}
  
  @Test
  public void addingItemWithNoInput() {
    final String[] item = new String[0];
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_INPUT;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithoutNameFlag() {
    final String[] item = {"-type", "RAW"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_INPUT;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithoutTypeFlag() {
    final String[] item = {"-name", "apple"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_INPUT;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithTwoFlagConsecutively() {
    final String[] item = {"-name", "-type", "apple", "RAW"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_INPUT;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  
  @Test
  public void addingItemWithMultipleNameEntries() {
    final String[] item = {"-name", "apple", "-name", "mango"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_INPUT;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithEmptyNameEntry() {
    final String[] item = {"-name", "", "-type", "RAW"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    final String expectedMessage = ExceptionsConstants.INVALID_NAME;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithNegativePrice() {
    final String[] item = {"-name", "apple", "-price", "-12", "-type", "raw"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_PRICE;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithCharacterPrice() {
    final String[] item = {"-name", "apple", "-price", "abc", "-type", "raw"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_PRICE;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithNegativeQuantity() {
    final String[] item = {"-name", "apple", "-quantity", "-12", "-type", "raw"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_QUANTITY;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithCharacterQuantity() {
    final String[] item = {"-name", "apple", "-quantity", "ab2", "-type", "raw"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_QUANTITY;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void addingItemWithWrongType() {
    final String[] item = {"-name", "apple", "-type", "market"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    
    final String expectedMessage = ExceptionsConstants.INVALID_TYPE;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void checkTaxForRawItemWhenItemPriceIsNegative() {
    final String[] item = {"-name", "apple", "-type", "RAW", "-price",
      "-12"};
    final Exception exception = assertThrows(CustomException.class,
        () -> new ItemAdderImpl().createItem(item));
    final String expectedMessage = ExceptionsConstants.INVALID_PRICE;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  public void checkTaxForRawItemWhenItemPriceIsPositive() throws CustomException {
    final String[] item = {"-name", "apple", "-price", "12", "-type", "raw",
      "-quantity", "12"};
    double actualRawItemTax = new ItemAdderImpl().createItem(item).getSalesTax();
    double expectedRawItemTax = 1.5;
    Assertions.assertEquals(actualRawItemTax, expectedRawItemTax);
  }
  
  
  @Test
  public void checkTaxForManufacturedItemWhenItemPriceIsPositive() throws CustomException {
    final String[] item = {"-name", "apple", "-price", "12", "-type", "manufactured"};
    double actualManufacturedItemTax = new ItemAdderImpl().createItem(item).getSalesTax();
    double expectedManufacturedItemTax = 1.77;
    Assertions.assertEquals(actualManufacturedItemTax, expectedManufacturedItemTax);
  }
  
  
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositiveAndUnder100() throws CustomException {
    final String[] item = {"-name", "apple", "-price", "12", "-type", "imported"};
    double actualImportedItemTax = new ItemAdderImpl().createItem(item).getSalesTax();
    double expectedImportedItemTax = 6.2;
    Assertions.assertEquals(actualImportedItemTax, expectedImportedItemTax);
  }
  
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositiveAndAbove100() throws CustomException {
    final String[] item = {"-name", "apple", "-price", "100", "-type",
      "imported"};
    double actualImportedItemTax = new ItemAdderImpl().createItem(item).getSalesTax();
    double expectedImportedItemTax = 20;
    Assertions.assertEquals(actualImportedItemTax, expectedImportedItemTax);
  }
  
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositiveAndAbove1000() throws CustomException {
    final String[] item = {"-name", "apple", "-price", "1000", "-type",
      "imported"};
    double actualImportedItemTax = new ItemAdderImpl().createItem(item).getSalesTax();
    double expectedImportedItemTax = 155;
    Assertions.assertEquals(actualImportedItemTax, expectedImportedItemTax);
  }
}