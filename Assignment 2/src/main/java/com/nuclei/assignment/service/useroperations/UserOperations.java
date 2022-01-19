package com.nuclei.assignment.service.useroperations;

import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;

import java.util.List;

/**
 * User CRUD Operations interface.
 */
public interface UserOperations {
  
  /**
   * Gets All users.
   *
   * @return the users
   */
  List<UserEntity> getUsers();
  
  /**
   * Add User to List.
   *
   * @param user the user
   * @throws CustomException the custom exception
   */
  void addUser(UserEntity user) throws CustomException;
  
  /**
   * Get User by roll number.
   *
   * @param rollNumber the roll number
   * @return the user by roll number
   * @throws CustomException the custom exception
   */
  UserEntity getUserByRollNumber(int rollNumber) throws CustomException;
  
  /**
   * Check whether a user exist by given roll number.
   *
   * @param rollNumber the roll number
   * @return the boolean
   * @throws CustomException the custom exception
   */
  boolean checkIfUserExistByRollNumber(int rollNumber) throws CustomException;
  
  /**
   * Delete User by roll number.
   *
   * @param rollNumber the roll number
   * @throws CustomException the custom exception
   */
  void deleteUserByRollNumber(int rollNumber) throws CustomException;
  
  /**
   * Save User to local disk.
   *
   * @throws CustomException the custom exception
   */
  void saveUsersToDisk() throws CustomException;
  
  /**
   * Sort user list based on column number and sorting order (asc/desc).
   *
   * @param columnNumber the column number
   *                     {1: Name, 2: Roll Number, 3: Age, 4: Address}
   * @param sortingOrder the sorting order {ASC/DESC}
   * @return the list
   */
  List<UserEntity> sortUsersBasedOnParameters(int columnNumber, SortingOrder sortingOrder);
}
