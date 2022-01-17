package com.nuclei.assignment.connections;

import com.nuclei.assignment.constants.DatabaseConstantsUtils;
import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
  public ResultSet getAllItems() throws DatabaseException {
    final ResultSet resultSet;
    try {
      final Statement statement = connection.createStatement();
      resultSet = statement.executeQuery(DatabaseConstantsUtils.SELECT_ALL_QUERY);
    } catch (SQLException exception) {
      logger.error(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA,
          exception.getMessage()));
      throw new DatabaseException(String.format(
          DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA, exception.getMessage()), exception);
    }
    return resultSet;
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
