package com.nuclei.assignment.service.displayitem;

import java.util.List;
import java.util.Map;

/**
 * The interface Display items synchronous threads.
 */
public interface DisplayItemsSynchronousThreads {
  
  /**
   * Display items.
   *
   * @param items the items
   * @throws InterruptedException the interrupted exception
   */
  void displayItems(List<Map<String,String>> items) throws InterruptedException;
}
