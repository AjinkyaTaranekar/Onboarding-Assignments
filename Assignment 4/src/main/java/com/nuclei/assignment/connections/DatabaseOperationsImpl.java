package com.nuclei.assignment.connections;

import com.nuclei.assignment.constants.DatabaseConstantsUtils;
import com.nuclei.assignment.constants.FlagsConstantsUtils;
import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The type Database operations.
 */
public class DatabaseOperationsImpl implements DatabaseOperations {
  
  /**
   * The database connection.
   */
  private Connection connection;
  
  
  /**
   * The logger.
   */
  private final Log logger = LogFactory.getLog(DatabaseOperationsImpl.class);
  
  /**
   * Instantiates a new Database operations.
   *
   * @throws DatabaseException the database exception
   */
  public DatabaseOperationsImpl() throws DatabaseException {
    connectDatabase();
  }
  
  private void connectDatabase() throws DatabaseException {
    try {
      Class.forName(DatabaseConstantsUtils.DATABASE_DRIVER);
      connection = DriverManager.getConnection(
          DatabaseConstantsUtils.HOST,
          DatabaseConstantsUtils.USERNAME,
          DatabaseConstantsUtils.PASSWORD
      );
      if (connection != null) {
        logger.info(DatabaseConstantsUtils.DATABASE_CONNECTED);
      }
    } catch (SQLException | ClassNotFoundException exception) {
      logger.error(String.format(DatabaseConstantsUtils.DATABASE_NOT_CONNECTED,
          exception.getMessage()));
      throw new DatabaseException(String.format(DatabaseConstantsUtils.DATABASE_NOT_CONNECTED,
          exception.getMessage()), exception);
    }
  }
  
  @Override
  public List<Map<String,String>> getAllItems() throws DatabaseException {
    final List<Map<String,String>> items = new ArrayList<>();
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(DatabaseConstantsUtils.SELECT_ALL_QUERY)
    ) {
      while (resultSet.next()) {
        items.add(createItemFromResultSet(resultSet));
      }
    } catch (SQLException exception) {
      logger.error(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA,
          exception.getMessage()));
      throw new DatabaseException(String.format(
          DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA, exception.getMessage()), exception);
    }
    return items;
  }
  
  
  private Map<String, String> createItemFromResultSet(ResultSet resultSet)
      throws SQLException {
    final Map<String, String> item = new ConcurrentHashMap<>();
    item.put(FlagsConstantsUtils.NAME_FLAG,
        resultSet.getString(FlagsConstantsUtils.NAME_FLAG.replace("-", "")));
    item.put(FlagsConstantsUtils.PRICE_FLAG,
        resultSet.getString(FlagsConstantsUtils.PRICE_FLAG.replace("-", "")));
    item.put(FlagsConstantsUtils.QUANTITY_FLAG,
        resultSet.getString(FlagsConstantsUtils.QUANTITY_FLAG.replace("-", "")));
    item.put(FlagsConstantsUtils.TYPE_FLAG,
        resultSet.getString(FlagsConstantsUtils.TYPE_FLAG.replace("-", "")));
    return item;
  }
  
  @Override
  public void saveItem(ItemEntity item) throws DatabaseException {
    try (Statement statement = connection.createStatement()) {
      final int resultSet =
          statement.executeUpdate(String.format(DatabaseConstantsUtils.INSERT_QUERY,
            item.getName(), item.getPrice(), item.getQuantity(), item.getType()));
      logger.info(String.format(DatabaseConstantsUtils.ITEM_SAVED,
            resultSet));
      System.out.println(String.format(DatabaseConstantsUtils.ITEM_SAVED,
            resultSet));
    } catch (SQLException exception) {
      logger.error(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_SAVING_DATA,
          exception.getMessage()));
      throw new DatabaseException(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_SAVING_DATA,
          exception.getMessage()), exception);
    }
  }
  
  @Override
  public void closeConnection() throws SQLException {
    connection.close();
  }
}
