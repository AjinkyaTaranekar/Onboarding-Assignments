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
    
    @Test
    public void rawItemTaxCalculationWhenItemPriceIsNegative() {
        Exception exception = assertThrows(CustomException.class, () -> new ItemTaxImpl().rawItemTaxCalculation(-12d));

        String expectedMessage = ExceptionsConstants.INVALID_PRICE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    
    @Test
    public void rawItemTaxCalculationWhenItemPriceIsPositive() throws CustomException {
        double actualRawItemTax = new ItemTaxImpl().rawItemTaxCalculation(12d);
        double expectedRawItemTax = 1.5;
        Assertions.assertEquals(actualRawItemTax, expectedRawItemTax);
    }

    
    @Test
    public void manufacturedItemTaxCalculationWhenItemPriceIsNegative() {
        Exception exception = assertThrows(CustomException.class, () -> new ItemTaxImpl().manufacturedItemTaxCalculation(-12d));

        String expectedMessage = ExceptionsConstants.INVALID_PRICE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    
    @Test
    public void manufacturedItemTaxCalculationWhenItemPriceIsPositive() throws CustomException {
        double actualManufacturedItemTax = new ItemTaxImpl().manufacturedItemTaxCalculation(12d);
        double expectedManufacturedItemTax = 1.77;
        Assertions.assertEquals(actualManufacturedItemTax, expectedManufacturedItemTax);
    }

    
    @Test
    public void importedItemTaxCalculationWhenItemPriceIsNegative() {
        Exception exception = assertThrows(CustomException.class, () -> new ItemTaxImpl().importedItemTaxCalculation(-12d));

        String expectedMessage = ExceptionsConstants.INVALID_PRICE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    
    @Test
    public void importedItemTaxCalculationWhenItemPriceIsPositive() throws CustomException {
        double actualImportedItemTax = new ItemTaxImpl().importedItemTaxCalculation(12d);
        double expectedImportedItemTax = 6.2;
        Assertions.assertEquals(actualImportedItemTax, expectedImportedItemTax);
    }
}
