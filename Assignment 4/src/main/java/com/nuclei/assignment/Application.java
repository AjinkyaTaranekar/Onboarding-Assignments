package com.nuclei.assignment;

import com.nuclei.assignment.exception.DatabaseException;
import com.nuclei.assignment.service.itemadder.ItemAdderImpl;

/**
 * Assignment 1.
 */
public final class Application {
  private Application() {
  
  }
  
  /**
   * Main Function to run the user interface.
   *
   * @param args the args
   */
  public static void main(final String[] args) {
    try {
      new ItemAdderImpl().inputItemsFromUserInterface(args);
    } catch (DatabaseException exception) {
      System.out.println(exception.getMessage());
    }
  }
}
