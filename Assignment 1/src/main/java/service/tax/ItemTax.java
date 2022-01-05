package service.tax;

import exception.CustomException;

/**
 * Item Tax interface for different types of Item Type
 * **/
public interface ItemTax {

    // Calculation of tax for ItemType.RAW
    double rawItemTaxCalculation(Double itemPrice) throws CustomException;

    // Calculation of tax for ItemType.MANUFACTURED
    double manufacturedItemTaxCalculation(Double itemPrice) throws CustomException;

    // Calculation of tax for ItemType.IMPORTED
    double importedItemTaxCalculation(Double itemPrice) throws CustomException;

}
