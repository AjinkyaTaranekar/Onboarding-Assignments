package com.nuclei.assignment.service.itemparser;

import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.CustomException;

/**
 * Item Parser interface.
 */
public interface ItemParser {
  
  
  /**
   * Parse raw String data to name.
   *
   * @param name the name
   * @return the string
   * @throws CustomException the custom exception
   */
  String parseName(String name) throws CustomException;
  
  /**
   * Parse raw String data to price.
   *
   * @param price the price
   * @return the double
   * @throws CustomException the custom exception
   */
  double parsePrice(String price) throws CustomException;
  
  /**
   * Parse raw String data to type.
   *
   * @param type the type
   * @return the item type
   * @throws CustomException the custom exception
   */
  ItemType parseType(String type) throws CustomException;
  
  /**
   * Parse raw String data to quantity.
   *
   * @param quantity the quantity
   * @return the double
   * @throws CustomException the custom exception
   */
  double parseQuantity(String quantity) throws CustomException;
}
