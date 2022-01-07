package com.nuclei.assignment.service.itemparser;

import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.CustomException;

/**
 * Item Parser interface.
 * **/
public interface ItemParser {
  
  
  /**
   * Parse raw String data to name.
   * **/
  String parseName(String name) throws CustomException;
  
  /**
   * Parse raw String data to price.
   * **/
  double parsePrice(String price) throws CustomException;
  
  /**
   * Parse raw String data to type.
   * **/
  ItemType parseType(String type) throws CustomException;
  
  /**
   * Parse raw String data to quantity.
   * **/
  double parseQuantity(String quantity) throws CustomException;
}
