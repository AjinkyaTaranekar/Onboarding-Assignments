package com.nuclei.assignment.service.graphoperations;

import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.exception.CustomException;

import java.util.Set;

/**
 * Graph operations Interface.
 */
public interface GraphOperations {
  
  /**
   * Get user By id from graph.
   *
   * @param id the id
   * @return the user by id
   * @throws CustomException the custom exception
   */
  UserEntity getUserById(int id) throws CustomException;
  
  /**
   * check user exist By id in graph.
   *
   * @param id the id
   * @return the boolean
   * @throws CustomException the custom exception
   */
  boolean checkUserExistById(int id) throws CustomException;
  
  /**
   * Create user graph.
   *
   * @param user the user
   * @throws CustomException the custom exception
   */
  void createUser(UserEntity user) throws CustomException;
  
  /**
   * Create Dependency between 2 user.
   *
   * @param parentId the parent id
   * @param childId  the child id
   * @throws CustomException the custom exception
   */
  void createDependency(int parentId, int childId) throws CustomException;
  
  /**
   * Get immediate parents of user By id from graph.
   *
   * @param id the id
   * @return the immediate parents
   * @throws CustomException the custom exception
   */
  Set<Integer> getImmediateParents(int id) throws CustomException;
  
  /**
   * Get immediate children of user By id from graph.
   *
   * @param id the id
   * @return the immediate children
   * @throws CustomException the custom exception
   */
  Set<Integer> getImmediateChildren(int id) throws CustomException;
  
  /**
   * Get all ancestors of user By id from graph.
   *
   * @param id the id
   * @return the all ancestors
   * @throws CustomException the custom exception
   */
  Set<Integer> getAllAncestors(int id) throws CustomException;
  
  /**
   * Get all descendants of user By id from graph.
   *
   * @param id the id
   * @return the all descendants
   * @throws CustomException the custom exception
   */
  Set<Integer> getAllDescendants(int id) throws CustomException;
  
  /**
   * Delete user By id from graph.
   *
   * @param id the id
   * @throws CustomException the custom exception
   */
  void deleteUserById(int id) throws CustomException;
  
  /**
   * Delete dependency between 2 user from graph.
   *
   * @param parentId the parent id
   * @param childId  the child id
   * @throws CustomException the custom exception
   */
  void deleteDependency(int parentId, int childId) throws CustomException;
}
