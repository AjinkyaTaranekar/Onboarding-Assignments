package com.nuclei.assignment.service.tax;

import com.nuclei.assignment.exception.CustomException;

/**
 * Item Tax interface for different types of Item Type.
 * **/
public interface ItemTax {
  
  /**
   * Item Tax interface for different types of Item Type.
   * **/
  double getTaxForRawItem(Double itemPrice) throws CustomException;
  
  /**
   * Calculation of tax for ItemType.MANUFACTURED.
   * **/
  double getTaxForManufacturedItem(Double itemPrice) throws CustomException;
  
  /**
   * Calculation of tax for ItemType.IMPORTED.
   * **/
  double getTaxForImportedItem(Double itemPrice) throws CustomException;
  
}
