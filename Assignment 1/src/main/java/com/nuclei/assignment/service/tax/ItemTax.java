package com.nuclei.assignment.service.tax;


/**
 * Item Tax interface for different types of Item Type.
 */
public interface ItemTax {
  
  
  /**
   * Gets tax for raw item.
   *
   * @param itemPrice the item price
   * @return the tax for raw item
   */
  double getTaxForRawItem(Double itemPrice);
  
  /**
   * Calculation of tax for ItemType.MANUFACTURED.
   *
   * @param itemPrice the item price
   * @return the tax for manufactured item
   */
  double getTaxForManufacturedItem(Double itemPrice);
  
  /**
   * Calculation of tax for ItemType.IMPORTED.
   *
   * @param itemPrice the item price
   * @return the tax for imported item
   */
  double getTaxForImportedItem(Double itemPrice);
  
}
