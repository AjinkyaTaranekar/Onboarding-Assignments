package com.nuclei.assignment.service.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Item Tax Tester to test business logic.
 */
public class ItemTaxTest {
  
  /**
   * Check tax for raw item when item price is positive.
   */
  @Test
  public void checkTaxForRawItemWhenItemPriceIsPositive() {
    double actualRawItemTax = new ItemTaxImpl().getTaxForRawItem(12d);
    double expectedRawItemTax = 1.5;
    assertEquals(actualRawItemTax, expectedRawItemTax);
  }
  
  
  /**
   * Check tax for manufactured item when item price is positive.
   */
  @Test
  public void checkTaxForManufacturedItemWhenItemPriceIsPositive() {
    double actualManufacturedItemTax = new ItemTaxImpl().getTaxForManufacturedItem(12d);
    double expectedManufacturedItemTax = 1.77;
    assertEquals(actualManufacturedItemTax, expectedManufacturedItemTax);
  }
  
  /**
   * Check tax for imported item when item price is below 100.
   */
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsBelow100() {
    double actualImportedItemTax = new ItemTaxImpl().getTaxForImportedItem(12d);
    double expectedImportedItemTax = 6.2;
    assertEquals(actualImportedItemTax, expectedImportedItemTax);
  }
  
  /**
   * Check tax for imported item when item price is between 100 and 200.
   */
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsBetween100And200() {
    double actualImportedItemTax =
        new ItemTaxImpl().getTaxForImportedItem(100d);
    double expectedImportedItemTax = 20;
    assertEquals(actualImportedItemTax, expectedImportedItemTax);
  }
  
  /**
   * Check tax for imported item when item price is above 200.
   */
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsAbove200() {
    double actualImportedItemTax =
        new ItemTaxImpl().getTaxForImportedItem(1000d);
    double expectedImportedItemTax = 155;
    assertEquals(actualImportedItemTax, expectedImportedItemTax);
  }
}