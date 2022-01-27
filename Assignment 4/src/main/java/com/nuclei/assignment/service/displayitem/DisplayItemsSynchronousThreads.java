package com.nuclei.assignment.service.displayitem;

import com.nuclei.assignment.exception.DatabaseException;

import java.util.List;
import java.util.Map;

/**
 * The interface Display items synchronous threads.
 */
public interface DisplayItemsSynchronousThreads {
  
  /**
   * Fetch item data from result set.
   *
   * @param items the items
   * @throws DatabaseException the database exception
   */
  void fetchItemDataFromRawData(List<Map<String,String>> items)
      throws DatabaseException;
  
  /**
   * Calculate tax for the items.
   *
   * @throws InterruptedException the interrupted exception
   */
  void calculateTaxForTheItems() throws InterruptedException;
}
