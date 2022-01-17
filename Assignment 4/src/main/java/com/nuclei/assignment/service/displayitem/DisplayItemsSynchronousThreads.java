package com.nuclei.assignment.service.displayitem;

import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.exception.DatabaseException;

import java.sql.ResultSet;

/**
 * The interface Display items synchronous threads.
 */
public interface DisplayItemsSynchronousThreads {
  
  /**
   * Fetch item data from result set.
   *
   * @param resultSet the result set
   * @throws DatabaseException       the database exception
   * @throws AttributeParseException the attribute parse exception
   * @throws InterruptedException    the interrupted exception
   */
  void fetchItemDataFromResultSet(ResultSet resultSet)
      throws DatabaseException, AttributeParseException, InterruptedException;
  
  /**
   * Calculate tax for the items.
   *
   * @throws InterruptedException the interrupted exception
   */
  void calculateTaxForTheItems() throws InterruptedException;
}
