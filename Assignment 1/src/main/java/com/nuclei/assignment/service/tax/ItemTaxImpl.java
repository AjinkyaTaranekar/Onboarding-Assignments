package com.nuclei.assignment.service.tax;

import com.nuclei.assignment.constants.ExceptionsConstants;
import com.nuclei.assignment.constants.ItemTaxConstants;
import com.nuclei.assignment.exception.CustomException;

/**
 * Item Tax interface for different types of Item Type.
 * **/
public class ItemTaxImpl implements ItemTax {
  
  @Override
  // Calculation of tax for ItemType.RAW
  public double getTaxForRawItem(final Double itemPrice) throws CustomException {
    checkItemPrice(itemPrice);
    return ItemTaxConstants.TAX_PERCENTAGE * itemPrice;
  }
  
  @Override
  // Calculation of tax for ItemType.MANUFACTURED
  public double getTaxForManufacturedItem(final Double itemPrice)
      throws CustomException {
    checkItemPrice(itemPrice);
    final double initialItemTax = ItemTaxConstants.TAX_PERCENTAGE * itemPrice;
    return initialItemTax + ItemTaxConstants.ADDITIONAL_TAX_PERCENTAGE
      * (itemPrice + initialItemTax);
  }
  
  @Override
  // Calculation of tax for ItemType.IMPORTED
  public double getTaxForImportedItem(final Double itemPrice) throws CustomException {
    checkItemPrice(itemPrice);
    final double initialItemTax =
        ItemTaxConstants.IMPORT_DUTY_TAX_PERCENTAGE * itemPrice;
    final double finalCost = itemPrice + initialItemTax;
    final double surcharge = calculateSurchargeBasedOnFinalCost(finalCost);
    return initialItemTax + surcharge;
  }
  
  private double calculateSurchargeBasedOnFinalCost(final Double finalCost) {
    double surcharge = ItemTaxConstants.SURCHARGE_BELOW_LOWER_LIMIT;
    if (finalCost > ItemTaxConstants.UPPER_LIMIT) {
      surcharge =
        ItemTaxConstants.SURCHARGE_TAX_PERCENTAGE_WHEN_UPPER_LIMIT_REACHED * finalCost;
    } else if (finalCost > ItemTaxConstants.LOWER_LIMIT) {
      surcharge = ItemTaxConstants.SURCHARGE_BETWEEN_LOWER_AND_UPPER_LIMIT;
    }
    return surcharge;
  }
  
  private void checkItemPrice(final Double itemPrice) throws CustomException {
    if (itemPrice < 0) {
      throw new CustomException(ExceptionsConstants.INVALID_PRICE);
    }
  }
}
