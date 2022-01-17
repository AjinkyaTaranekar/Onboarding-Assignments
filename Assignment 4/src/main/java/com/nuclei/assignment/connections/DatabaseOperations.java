package com.nuclei.assignment.connections;

import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.exception.DatabaseException;

import java.util.List;

/**
 * The interface Database operations.
 */
public interface DatabaseOperations {
  
  /**
   * Gets all items.
   *
   * @return the all items
   * @throws DatabaseException       the database exception
   * @throws AttributeParseException the attribute parse exception
   */
  List<ItemEntity> getAllItems() throws DatabaseException, AttributeParseException;
  
  /**
   * Check whether all items are fetched boolean.
   *
   * @return the boolean
   */
  boolean checkWhetherAllItemsAreFetched();
  
  /**
   * Save item.
   *
   * @param item the item
   * @throws DatabaseException the database exception
   */
  void saveItem(ItemEntity item) throws DatabaseException;
}
