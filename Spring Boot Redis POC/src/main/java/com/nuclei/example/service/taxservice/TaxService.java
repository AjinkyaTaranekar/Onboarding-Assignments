package com.nuclei.example.service.taxservice;

/**
 * The interface Tax service.
 */
public interface TaxService {
  /**
   * Gets tax for raw inventory.
   *
   * @param inventoryPrice the inventory price
   *
   * @return the tax for raw inventory
   */
  double getTaxForRawInventory (Double inventoryPrice);
  
  /**
   * Gets tax for manufactured inventory.
   *
   * @param inventoryPrice the inventory price
   *
   * @return the tax for manufactured inventory
   */
  double getTaxForManufacturedInventory (Double inventoryPrice);
  
  /**
   * Gets tax for imported inventory.
   *
   * @param inventoryPrice the inventory price
   *
   * @return the tax for imported inventory
   */
  double getTaxForImportedInventory (Double inventoryPrice);
}
