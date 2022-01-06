package service.itemparser;

import enums.ItemType;
import exception.CustomException;

/**
 * Item Parser interface.
 * **/
public interface ItemParser {
  
  String parseName(String name) throws CustomException;
  
  double parsePrice(String price) throws CustomException;
  
  ItemType parseType(String type) throws CustomException;
  
  double parseQuantity(String quantity) throws CustomException;
}
