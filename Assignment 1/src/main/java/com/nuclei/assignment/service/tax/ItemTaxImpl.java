package com.nuclei.assignment.service.tax;

import com.nuclei.assignment.constants.ItemTaxConstantsUtils;

/**
 * Item Tax interface for different types of Item Type.
 */
public class ItemTaxImpl implements ItemTax {
  
  @Override
  public double getTaxForRawItem(final Double itemPrice) {
    return ItemTaxConstantsUtils.TAX_PERCENTAGE * itemPrice;
  }
  
  @Override
  public double getTaxForManufacturedItem(final Double itemPrice) {
    final double initialItemTax = ItemTaxConstantsUtils.TAX_PERCENTAGE * itemPrice;
    return initialItemTax + ItemTaxConstantsUtils.ADDITIONAL_TAX_PERCENTAGE
      * (itemPrice + initialItemTax);
  }
  
  @Override
  public double getTaxForImportedItem(final Double itemPrice) {
    final double initialItemTax =
        ItemTaxConstantsUtils.IMPORT_DUTY_TAX_PERCENTAGE * itemPrice;
    final double finalCost = itemPrice + initialItemTax;
    final double surcharge = calculateSurchargeBasedOnFinalCost(finalCost);
    return initialItemTax + surcharge;
  }
  
  private double calculateSurchargeBasedOnFinalCost(final Double finalCost) {
    double surcharge = ItemTaxConstantsUtils.SURCHARGE_BELOW_LOWER_LIMIT;
    if (finalCost > ItemTaxConstantsUtils.UPPER_LIMIT) {
      surcharge =
        ItemTaxConstantsUtils.SURCHARGE_TAX_PERCENTAGE_WHEN_UPPER_LIMIT_REACHED * finalCost;
    } else if (finalCost > ItemTaxConstantsUtils.LOWER_LIMIT) {
      surcharge = ItemTaxConstantsUtils.SURCHARGE_BETWEEN_LOWER_AND_UPPER_LIMIT;
    }
    return surcharge;
  }
}
