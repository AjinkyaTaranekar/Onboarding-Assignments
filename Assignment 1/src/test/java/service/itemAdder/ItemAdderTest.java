package service.itemAdder;

import constants.ExceptionsConstants;
import exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Item Adder Tester containing various testcases while adding items.
 * **/
public class ItemAdderTest {
    // build item string as name,price,quantity,type
    @Test
    public void addingItemWithNoInput() {
        String item = "";
        Exception exception = assertThrows(CustomException.class, () -> new ItemAdderImpl().createItem(item));

        String expectedMessage = ExceptionsConstants.INVALID_INPUT;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void addingItemWithIncompleteInput() {
        String item = "apple,-12,12";
        Exception exception = assertThrows(CustomException.class, () -> new ItemAdderImpl().createItem(item));

        String expectedMessage = ExceptionsConstants.INVALID_INPUT;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void addingItemWithNegativePrice() {
        String item = "apple,-12,12,RAW";
        Exception exception = assertThrows(CustomException.class, () -> new ItemAdderImpl().createItem(item));

        String expectedMessage = ExceptionsConstants.INVALID_PRICE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void addingItemWithCharacterPrice() {
        String item = "apple,abc,12,RAW";
        Exception exception = assertThrows(CustomException.class, () -> new ItemAdderImpl().createItem(item));

        String expectedMessage = ExceptionsConstants.INVALID_PRICE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void addingItemWithNegativeQuantity() {
        String item = "apple,12,-12,RAW";
        Exception exception = assertThrows(CustomException.class, () -> new ItemAdderImpl().createItem(item));

        String expectedMessage = ExceptionsConstants.INVALID_QUANTITY;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void addingItemWithCharacterQuantity() {
        String item = "apple,12,xyz,RAW";
        Exception exception = assertThrows(CustomException.class, () -> new ItemAdderImpl().createItem(item));

        String expectedMessage = ExceptionsConstants.INVALID_QUANTITY;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void addingItemWithWrongType() {
        String item = "apple,12,12,market";
        Exception exception = assertThrows(CustomException.class, () -> new ItemAdderImpl().createItem(item));

        String expectedMessage = ExceptionsConstants.INVALID_TYPE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void rawItemTaxCalculationWhenItemPriceIsPositive() throws CustomException {
        String item = "apple,12,12,RAW";
        double actualRawItemTax = new ItemAdderImpl().createItem(item).getSalesTax();
        double expectedRawItemTax = 1.5;
        Assertions.assertEquals(actualRawItemTax, expectedRawItemTax);
    }


    @Test
    public void manufacturedItemTaxCalculationWhenItemPriceIsPositive() throws CustomException {
        String item = "apple,12,12,MANUFACTURED";
        double actualManufacturedItemTax = new ItemAdderImpl().createItem(item).getSalesTax();
        double expectedManufacturedItemTax = 1.77;
        Assertions.assertEquals(actualManufacturedItemTax, expectedManufacturedItemTax);
    }


    @Test
    public void importedItemTaxCalculationWhenItemPriceIsPositive() throws CustomException {
        String item = "apple,12,12,IMPORTED";
        double actualImportedItemTax = new ItemAdderImpl().createItem(item).getSalesTax();
        double expectedImportedItemTax = 6.2;
        Assertions.assertEquals(actualImportedItemTax, expectedImportedItemTax);
    }
}
