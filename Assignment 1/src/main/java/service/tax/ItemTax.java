package service.tax;

import exception.CustomException;

/**
 * Item Tax interface for different types of Item Type.
 * **/
public interface ItemTax {
  
  // Calculation of tax for ItemType.RAW
  double getTaxForRawItem(Double itemPrice) throws CustomException;
  
  // Calculation of tax for ItemType.MANUFACTURED
  double getTaxForManufacturedItem(Double itemPrice) throws CustomException;
  
  // Calculation of tax for ItemType.IMPORTED
  double getTaxForImportedItem(Double itemPrice) throws CustomException;
  
}
