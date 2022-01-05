package service.tax;

import constants.ExceptionsConstants;
import exception.CustomException;

/**
 * Item Tax interface for different types of Item Type
 * **/
public class ItemTaxImpl implements ItemTax {

    static double taxPercentage = 12.5 / 100;
    static double additionalTaxPercentage = 2.0 / 100;
    static double importDutyTaxPercentage = 10.0 / 100;
    static double surchargeTaxPercentageWhenExceedTwoHundred = 5.0 / 100;

    @Override
    // Calculation of tax for ItemType.RAW
    public double rawItemTaxCalculation(Double itemPrice) throws CustomException {
        checkItemPrice(itemPrice);
        return taxPercentage * itemPrice;
    }

    @Override
    // Calculation of tax for ItemType.MANUFACTURED
    public double manufacturedItemTaxCalculation(Double itemPrice) throws CustomException {
        checkItemPrice(itemPrice);
        double initialItemTax = taxPercentage * itemPrice;
        return initialItemTax + additionalTaxPercentage * (itemPrice + initialItemTax);
    }

    @Override
    // Calculation of tax for ItemType.IMPORTED
    public double importedItemTaxCalculation(Double itemPrice) throws CustomException {
        checkItemPrice(itemPrice);
        double initialItemTax = importDutyTaxPercentage * itemPrice;
        double finalCost = itemPrice + initialItemTax;
        double surcharge = calculateSurchargeBasedOnFinalCost(finalCost);
        return initialItemTax + surcharge;
    }

    private double calculateSurchargeBasedOnFinalCost(Double finalCost) {
        if (finalCost > 200) {
            return surchargeTaxPercentageWhenExceedTwoHundred * finalCost;
        } else if ( finalCost > 100) {
            return 10;
        }
        return 5;
    }

    private void checkItemPrice(Double itemPrice) throws CustomException {
        if (itemPrice < 0)
            throw new CustomException(ExceptionsConstants.INVALID_PRICE);
    }
}
