package com.nuclei.assignment.connections;

import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.exception.DatabaseException;

import java.sql.ResultSet;

/**
 * The interface Database operations.
 */
public interface DatabaseOperations {
  
  /**
   * Gets all items.
   *
   * @return the all items result set
   * @throws DatabaseException       the database exception
   */
  ResultSet getAllItems() throws DatabaseException, AttributeParseException;
  
  /**
   * Save item.
   *
   * @param item the item
   * @throws DatabaseException the database exception
   */
  void saveItem(ItemEntity item) throws DatabaseException;
}
