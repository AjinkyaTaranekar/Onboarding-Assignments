package com.nuclei.assignment.service.itemadder;

import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.exception.DatabaseException;
import com.nuclei.assignment.exception.InputException;


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
   * @throws InputException          the custom exception
   * @throws AttributeParseException the attribute parse exception
   * @throws DatabaseException       the database exception
   */
  void outputItemsWithTaxToUser() throws InputException, AttributeParseException, DatabaseException;
}
