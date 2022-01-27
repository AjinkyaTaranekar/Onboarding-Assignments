package com.nuclei.assignment.repository;

import com.nuclei.assignment.connections.DatabaseOperations;
import com.nuclei.assignment.constants.DatabaseConstantsUtils;
import com.nuclei.assignment.constants.FlagsConstantsUtils;
import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.exception.DatabaseException;

import java.sql.Connection;
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
 * The type Item repository.
 */
public class ItemRepositoryImpl implements ItemRepository {
  
  /**
   * The logger.
   */
  private final Log logger = LogFactory.getLog(ItemRepositoryImpl.class);
  
  /**
   * The Connection.
   */
  private final Connection connection;
  
  /**
   * Instantiates a new Item repository.
   *
   * @param databaseOperations the database operations
   */
  public ItemRepositoryImpl(DatabaseOperations databaseOperations) {
    connection = databaseOperations.getConnection();
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
      System.out.printf((DatabaseConstantsUtils.ITEM_SAVED) + "%n", resultSet);
    } catch (SQLException exception) {
      logger.error(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_SAVING_DATA,
          exception.getMessage()));
      throw new DatabaseException(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_SAVING_DATA,
          exception.getMessage()), exception);
    }
  }
  
}
