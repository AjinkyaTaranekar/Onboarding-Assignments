package com.nuclei.assignment.connections;

import com.nuclei.assignment.constants.DatabaseConstantsUtils;
import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.exception.DatabaseException;
import com.nuclei.assignment.service.itemparser.ItemParser;
import com.nuclei.assignment.service.itemparser.ItemParserImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The type Database operations.
 */
public class DatabaseOperationsImpl implements DatabaseOperations {
  
  private Connection connection;
  
  private final Log logger = LogFactory.getLog(DatabaseOperationsImpl.class);
  
  private Boolean allItemsFetchedFromDatabase = false;
  
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
  public List<ItemEntity> getAllItems() throws DatabaseException, AttributeParseException {
    List<ItemEntity> itemEntities = new ArrayList<>();
    ItemParser itemParser = new ItemParserImpl();
    try {
      final Statement statement = connection.createStatement();
      final ResultSet resultSet =
          statement.executeQuery(DatabaseConstantsUtils.SELECT_ALL_QUERY);
      while (resultSet.next()) {
        final String itemName =
            itemParser.parseName(resultSet.getString("name"));
        final double itemPrice = itemParser.parsePrice(resultSet.getString(
            "price"));
        final double itemQuantity = itemParser.parseQuantity(resultSet.getString(
            "quantity"));
        final ItemType itemType = itemParser.parseType(resultSet.getString("type"));
        final ItemEntity item = new ItemEntity(itemName, itemPrice,
            itemQuantity, itemType);
        itemEntities.add(item);
      }
    } catch (SQLException exception) {
      logger.error(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA,
          exception.getMessage()));
      throw new DatabaseException(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA,
          exception.getMessage()), exception);
    } catch (AttributeParseException exception) {
      throw new AttributeParseException(exception.getMessage(), exception);
    }
    allItemsFetchedFromDatabase = true;
    logger.info(String.format(DatabaseConstantsUtils.ITEMS_FETCHED,
        itemEntities));
    System.out.println(String.format(DatabaseConstantsUtils.ITEMS_FETCHED,
        itemEntities));
    return itemEntities;
  }
  
  @Override
  public boolean checkWhetherAllItemsAreFetched() {
    return allItemsFetchedFromDatabase;
  }
  
  @Override
  public void saveItem(ItemEntity item) throws DatabaseException {
    try {
      final Statement statement = connection.createStatement();
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
}
