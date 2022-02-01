package com.nuclei.assignment.repository;

import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.exception.DatabaseException;

import java.util.List;
import java.util.Map;

/**
 * The interface Item repository.
 */
public interface ItemRepository {
  
  /**
   * Gets all items.
   *
   * @return the all items result set
   * @throws DatabaseException       the database exception
   * @throws AttributeParseException the attribute parse exception
   */
  List<Map<String,String>> getAllItems() throws DatabaseException, AttributeParseException;
  
  /**
   * Save item.
   *
   * @param item the item
   * @throws DatabaseException the database exception
   */
  void saveItem(ItemEntity item) throws DatabaseException;
}
