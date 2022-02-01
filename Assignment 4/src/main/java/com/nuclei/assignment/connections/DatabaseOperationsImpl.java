package com.nuclei.assignment.connections;

import com.nuclei.assignment.constants.DatabaseConstantsUtils;
import com.nuclei.assignment.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
  
  @Override
  public void createConnection() throws DatabaseException {
    try {
      Class.forName(DatabaseConstantsUtils.DATABASE_DRIVER);
      connection = DriverManager.getConnection(
          DatabaseConstantsUtils.HOST,
          DatabaseConstantsUtils.USERNAME,
          DatabaseConstantsUtils.PASSWORD
      );
      if (connection != null) {
        System.out.println(DatabaseConstantsUtils.DATABASE_CONNECTED);
      }
    } catch (SQLException | ClassNotFoundException exception) {
      logger.error(String.format(DatabaseConstantsUtils.DATABASE_NOT_CONNECTED,
          exception.getMessage()));
      throw new DatabaseException(String.format(DatabaseConstantsUtils.DATABASE_NOT_CONNECTED,
          exception.getMessage()), exception);
    }
  }
  
  @Override
  public Connection getConnection() {
    return connection;
  }
  
  @Override
  public void closeConnection() throws SQLException {
    connection.close();
  }
}
