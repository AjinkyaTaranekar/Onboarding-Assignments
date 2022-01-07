package com.nuclei.assignment;

import com.nuclei.assignment.service.itemadder.ItemAdderImpl;

/**
 * Assignment 1.
 * */
public final class Application {
  private Application() {
  
  }
  
  /**
   * Main Function to run the user interface.
   * **/
  public static void main(final String[] args) {
    new ItemAdderImpl().inputItemsFromUserInterface(args);
  }
}
