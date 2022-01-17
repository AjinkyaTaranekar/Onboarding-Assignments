package com.nuclei.assignment.constants;

/**
 * The type Database constants.
 */
public class DatabaseConstantsUtils {
  
  /**
   * The constant HOST.
   */
  public static final String HOST = "jdbc:mysql://localhost:3306/items";
  /**
   * The constant USERNAME.
   */
  public static final String USERNAME = "root";
  /**
   * The constant PASSWORD.
   */
  public static final String PASSWORD = "";
  
  
  /**
   * The constant DATABASE_CONNECTED.
   */
  public static final String DATABASE_CONNECTED = "MySQL DataBase connected!";
  /**
   * The constant DATABASE_NOT_CONNECTED.
   */
  public static final String DATABASE_NOT_CONNECTED =
      "Connection failed with exception %s";
  /**
   * The constant EXCEPTION_WHILE_FETCHING_DATA.
   */
  public static final String EXCEPTION_WHILE_FETCHING_DATA =
      "Exception occurred while fetching data %s";
  /**
   * The constant EXCEPTION_WHILE_SAVING_DATA.
   */
  public static final String EXCEPTION_WHILE_SAVING_DATA =
      "Exception occurred while saving data %s";
  
  /**
   * The constant ITEM_SAVED.
   */
  public static final String ITEM_SAVED = "%s Item data saved";
  /**
   * The constant ITEMS_FETCHED.
   */
  public static final String ITEMS_FETCHED = "Items data fetched %s";
  
  /**
   * The constant DATABASE_DRIVER.
   */
  public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
  
  /**
   * The constant INSERT_QUERY.
   */
  public static final String INSERT_QUERY =
      "INSERT INTO `items` (`name`, `price`, `quantity`, `type`) "
        + "VALUES (\"%s\", %s, %s, \"%s\");";
  /**
   * The constant SELECT_ALL_QUERY.
   */
  public static final String SELECT_ALL_QUERY =
      "SELECT * FROM `items`";
}
