package com.nuclei.assignment.service.itemparser;

import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.AttributeParseException;

/**
 * Item Parser interface.
 */
public interface ItemParser {
  
  
  /**
   * Parse raw String data to name.
   *
   * @param name the name
   * @return the string
   * @throws AttributeParseException the attribute parser exception
   */
  String parseName(String name) throws AttributeParseException;
  
  /**
   * Parse raw String data to price.
   *
   * @param price the price
   * @return the double
   * @throws AttributeParseException the attribute parser exception
   */
  double parsePrice(String price) throws AttributeParseException;
  
  /**
   * Parse raw String data to type.
   *
   * @param type the type
   * @return the item type
   * @throws AttributeParseException the attribute parser exception
   */
  ItemType parseType(String type) throws AttributeParseException;
  
  /**
   * Parse raw String data to quantity.
   *
   * @param quantity the quantity
   * @return the double
   * @throws AttributeParseException the attribute parser exception
   */
  double parseQuantity(String quantity) throws AttributeParseException;
}
