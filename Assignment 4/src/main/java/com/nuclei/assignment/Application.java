package com.nuclei.assignment;

import com.nuclei.assignment.service.itemadder.ItemAdderImpl;

/**
 * Assignment 4.
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
    new ItemAdderImpl().inputItemsFromUserInterface(args);
  }
}
