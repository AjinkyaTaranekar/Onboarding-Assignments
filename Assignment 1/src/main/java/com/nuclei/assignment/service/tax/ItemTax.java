package com.nuclei.assignment.service.tax;

import com.nuclei.assignment.exception.CustomException;

/**
 * Item Tax interface for different types of Item Type.
 */
public interface ItemTax {
  
  
  /**
   * Gets tax for raw item.
   *
   * @param itemPrice the item price
   * @return the tax for raw item
   * @throws CustomException the custom exception
   */
  double getTaxForRawItem(Double itemPrice) throws CustomException;
  
  /**
   * Calculation of tax for ItemType.MANUFACTURED.
   *
   * @param itemPrice the item price
   * @return the tax for manufactured item
   * @throws CustomException the custom exception
   */
  double getTaxForManufacturedItem(Double itemPrice) throws CustomException;
  
  /**
   * Calculation of tax for ItemType.IMPORTED.
   *
   * @param itemPrice the item price
   * @return the tax for imported item
   * @throws CustomException the custom exception
   */
  double getTaxForImportedItem(Double itemPrice) throws CustomException;
  
}
