package service.tax;

import constants.ExceptionsConstants;
import constants.ItemTaxConstants;
import exception.CustomException;

/**
 * Item Tax interface for different types of Item Type.
 * **/
public class ItemTaxImpl implements ItemTax {
  public ItemTaxImpl() {}
  
  @Override
  // Calculation of tax for ItemType.RAW
  public double getTaxForRawItem(Double itemPrice) throws CustomException {
    checkItemPrice(itemPrice);
    return ItemTaxConstants.TAX_PERCENTAGE * itemPrice;
  }
  
  @Override
  // Calculation of tax for ItemType.MANUFACTURED
  public double getTaxForManufacturedItem(Double itemPrice)
      throws CustomException {
    checkItemPrice(itemPrice);
    final double initialItemTax = ItemTaxConstants.TAX_PERCENTAGE * itemPrice;
    return initialItemTax + ItemTaxConstants.ADDITIONAL_TAX_PERCENTAGE
      * (itemPrice + initialItemTax);
  }
  
  @Override
  // Calculation of tax for ItemType.IMPORTED
  public double getTaxForImportedItem(Double itemPrice) throws CustomException {
    checkItemPrice(itemPrice);
    final double initialItemTax =
        ItemTaxConstants.IMPORT_DUTY_TAX_PERCENTAGE * itemPrice;
    final double finalCost = itemPrice + initialItemTax;
    final double surcharge = calculateSurchargeBasedOnFinalCost(finalCost);
    return initialItemTax + surcharge;
  }
  
  private double calculateSurchargeBasedOnFinalCost(Double finalCost) {
    double surcharge = ItemTaxConstants.SURCHARGE_BELOW_LOWER_LIMIT;
    if (finalCost > ItemTaxConstants.UPPER_LIMIT) {
      surcharge =
        ItemTaxConstants.SURCHARGE_TAX_PERCENTAGE_WHEN_UPPER_LIMIT_REACHED * finalCost;
    } else if (finalCost > ItemTaxConstants.LOWER_LIMIT) {
      surcharge = ItemTaxConstants.SURCHARGE_BETWEEN_LOWER_AND_UPPER_LIMIT;
    }
    return surcharge;
  }
  
  private void checkItemPrice(Double itemPrice) throws CustomException {
    if (itemPrice < 0) {
      throw new CustomException(ExceptionsConstants.INVALID_PRICE);
    }
  }
}
