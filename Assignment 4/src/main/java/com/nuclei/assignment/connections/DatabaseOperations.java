package com.nuclei.assignment.connections;

import com.nuclei.assignment.exception.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The interface Database operations.
 */
public interface DatabaseOperations {
  
  /**
   * Create connection.
   *
   * @throws DatabaseException the database exception
   */
  void createConnection() throws DatabaseException;
  
  /**
   * Gets connection.
   *
   * @return the connection
   */
  Connection getConnection();
  
  /**
   * Close connection.
   *
   * @throws SQLException the sql exception
   */
  void closeConnection() throws SQLException;
}
