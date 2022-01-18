package com.nuclei.assignment.service.itemadder;


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
   */
  void outputItemsWithTaxToUser();
}
