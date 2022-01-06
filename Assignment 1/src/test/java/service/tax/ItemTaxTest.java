package service.tax;

import constants.ExceptionsConstants;
import exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Item Tax Tester to test business logic
 * **/
public class ItemTaxTest {
  public ItemTaxTest() {}
  
  @Test
  public void checkTaxForRawItemWhenItemPriceIsNegative() {
    final Exception exception = assertThrows(CustomException.class, () -> new ItemTaxImpl().getTaxForRawItem(-12d));
    
    final String expectedMessage = ExceptionsConstants.INVALID_PRICE;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  
  @Test
  public void checkTaxForRawItemWhenItemPriceIsPositive() throws CustomException {
    double actualRawItemTax = new ItemTaxImpl().getTaxForRawItem(12d);
    double expectedRawItemTax = 1.5;
    Assertions.assertEquals(actualRawItemTax, expectedRawItemTax);
  }
  
  
  @Test
  public void checkTaxForManufacturedItemWhenItemPriceIsNegative() {
    final Exception exception = assertThrows(CustomException.class, () -> new ItemTaxImpl().getTaxForManufacturedItem(-12d));
    
    final String expectedMessage = ExceptionsConstants.INVALID_PRICE;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  
  @Test
  public void checkTaxForManufacturedItemWhenItemPriceIsPositive() throws CustomException {
    double actualManufacturedItemTax = new ItemTaxImpl().getTaxForManufacturedItem(12d);
    double expectedManufacturedItemTax = 1.77;
    Assertions.assertEquals(actualManufacturedItemTax, expectedManufacturedItemTax);
  }
  
  
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsNegative() {
    final Exception exception = assertThrows(CustomException.class, () -> new ItemTaxImpl().getTaxForImportedItem(-12d));
    
    final String expectedMessage = ExceptionsConstants.INVALID_PRICE;
    final String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  
  @Test
  public void checkTaxForImportedItemWhenItemPriceIsPositive() throws CustomException {
    double actualImportedItemTax = new ItemTaxImpl().getTaxForImportedItem(12d);
    double expectedImportedItemTax = 6.2;
    Assertions.assertEquals(actualImportedItemTax, expectedImportedItemTax);
  }
}
