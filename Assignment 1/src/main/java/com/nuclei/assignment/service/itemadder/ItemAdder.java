package com.nuclei.assignment.service.itemadder;

import com.nuclei.assignment.entity.Item;
import com.nuclei.assignment.exception.CustomException;

import java.util.List;

/**
 * Item Adder interface.
 * **/
public interface ItemAdder {
  
  /**
   * User Interface to input items.
   * **/
  void inputItemsFromUserInterface(String... rawData);
  
  /**
   * User Interface to output items with taxes.
   * **/
  void outputItemsWithTaxToUser(List<Item> items) throws CustomException;
}
