package com.nuclei.assignment.service.itemadder;

import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.exception.InputException;

import java.util.List;

/**
 * Item Adder interface.
 */
public interface ItemAdder {
  
  /**
   * User Interface to input items.
   *
   * @param rawData the raw data
   */
  void inputItemsFromUserInterface(String... rawData);
  
  /**
   * User Interface to output items with taxes.
   *
   * @param items the items
   * @throws InputException the custom exception
   */
  void outputItemsWithTaxToUser(List<ItemEntity> items) throws InputException;
}
