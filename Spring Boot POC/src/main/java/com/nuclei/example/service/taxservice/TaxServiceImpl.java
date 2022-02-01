package com.nuclei.example.service.taxservice;

import com.nuclei.example.constants.InventoryTaxConstantsUtils;
import org.springframework.stereotype.Service;

/**
 * The type Tax service.
 */
@Service
public class TaxServiceImpl implements TaxService {
  
  @Override
  public double getTaxForRawInventory (Double inventoryPrice) {
    return InventoryTaxConstantsUtils.TAX_PERCENTAGE * inventoryPrice;
  }
  
  @Override
  public double getTaxForManufacturedInventory (Double inventoryPrice) {
    final double initialInventoryTax = InventoryTaxConstantsUtils.TAX_PERCENTAGE * inventoryPrice;
    return initialInventoryTax + InventoryTaxConstantsUtils.ADDITIONAL_TAX_PERCENTAGE
        * (inventoryPrice + initialInventoryTax);
  }
  
  @Override
  public double getTaxForImportedInventory (Double inventoryPrice) {
    final double initialInventoryTax =
        InventoryTaxConstantsUtils.IMPORT_DUTY_TAX_PERCENTAGE * inventoryPrice;
    final double finalCost = inventoryPrice + initialInventoryTax;
    final double surcharge = calculateSurchargeBasedOnFinalCost(finalCost);
    return initialInventoryTax + surcharge;
  }
  
  private double calculateSurchargeBasedOnFinalCost (final Double finalCost) {
    double surcharge = InventoryTaxConstantsUtils.SURCHARGE_BELOW_LOWER_LIMIT;
    if (finalCost > InventoryTaxConstantsUtils.UPPER_LIMIT) {
      surcharge =
          InventoryTaxConstantsUtils.SURCHARGE_TAX_PERCENTAGE_WHEN_UPPER_LIMIT_REACHED * finalCost;
    } else if (finalCost > InventoryTaxConstantsUtils.LOWER_LIMIT) {
      surcharge = InventoryTaxConstantsUtils.SURCHARGE_BETWEEN_LOWER_AND_UPPER_LIMIT;
    }
    return surcharge;
  }
  
}
