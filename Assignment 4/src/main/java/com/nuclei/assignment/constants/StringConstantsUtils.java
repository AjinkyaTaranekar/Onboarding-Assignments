package com.nuclei.assignment.constants;

/**
 * Constant Strings used in code to provide user a brief
 * of what to do during input.
 */
public class StringConstantsUtils {
  
  /**
   * The constant ITEM_DETAILS_INFO.
   */
  public static final String[] ITEM_DETAILS_INFO = {
    "Item Details ",
    " NOTE: Enter details of Item based on flags",
    "-name <first item name>",
    "-price <price of first item>",
    "-quantity <quantity of first item>",
    "-type <type of first item>",
    "Note `type` to be inputted as"
  };
  
  /**
   * The constant ITEM_TYPES.
   */
  public static final String ITEM_TYPES = "%d. %s\n";
  
  /**
   * The constant INSERT_ITEM_DETAILS.
   */
  public static final String INSERT_ITEM_DETAILS = "Insert Item Details:";
  
  /**
   * The constant ADD_MORE_ITEMS_QUERY.
   */
  public static final String ADD_MORE_ITEMS_QUERY
      = "Do you want to enter details of any other item (y/n):";
  
  /**
   * The constant CONFIRMATION.
   */
  public static final String CONFIRMATION = "y";
  
  /**
   * The constant SHOW_ITEM_LIST.
   */
  public static final String SHOW_ITEM_LIST = "Here is the list of items:";
  
  /**
   * The constant SHOW_ITEM_LIST_INFO.
   */
  public static final String SHOW_ITEM_LIST_INFO
      = "%d. %s Item %s with price %.2f and quantity %.2f at tax %.2f => final price as %.2f\n";
  
  /**
   * The constant DIVIDER.
   */
  public static final String DIVIDER = "----------------------------------------------";
}