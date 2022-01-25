package com.nuclei.assignment.service.graphoperations;

import com.nuclei.assignment.entity.NodeEntity;
import com.nuclei.assignment.exception.CustomException;

import java.util.Map;
import java.util.Set;

/**
 * Graph operations Interface.
 */
public interface GraphOperations {
  
  /**
   * Get all nodes from graph.
   *
   * @return the all nodes
   */
  Map<Integer, NodeEntity> getAllNodes();
  
  /**
   * Get node By id from graph.
   *
   * @param id the id
   * @return the node by id
   * @throws CustomException the custom exception
   */
  NodeEntity getNodeById(int id) throws CustomException;
  
  /**
   * check node exist By id in graph.
   *
   * @param id the id
   * @return the boolean
   * @throws CustomException the custom exception
   */
  boolean checkNodeExistById(int id) throws CustomException;
  
  /**
   * Create node graph.
   *
   * @param node the node
   */
  void createNode(NodeEntity node) throws CustomException;
  
  /**
   * Create Dependency between 2 node.
   *
   * @param parentId the parent id
   * @param childId  the child id
   * @throws CustomException the custom exception
   */
  void createDependency(int parentId, int childId) throws CustomException;
  
  /**
   * Get immediate parents of node By id from graph.
   *
   * @param id the id
   * @return the immediate parents
   * @throws CustomException the custom exception
   */
  Set<Integer> getImmediateParents(int id) throws CustomException;
  
  /**
   * Get immediate children of node By id from graph.
   *
   * @param id the id
   * @return the immediate children
   * @throws CustomException the custom exception
   */
  Set<Integer> getImmediateChildren(int id) throws CustomException;
  
  /**
   * Get all ancestors of node By id from graph.
   *
   * @param id the id
   * @return the all ancestors
   * @throws CustomException the custom exception
   */
  Set<Integer> getAllAncestors(int id) throws CustomException;
  
  /**
   * Get all descendants of node By id from graph.
   *
   * @param id the id
   * @return the all descendants
   * @throws CustomException the custom exception
   */
  Set<Integer> getAllDescendants(int id) throws CustomException;
  
  /**
   * Delete node By id from graph.
   *
   * @param id the id
   * @throws CustomException the custom exception
   */
  void deleteNodeById(int id) throws CustomException;
  
  /**
   * Delete dependency between 2 node from graph.
   *
   * @param parentId the parent id
   * @param childId  the child id
   * @throws CustomException the custom exception
   */
  void deleteDependency(int parentId, int childId) throws CustomException;
}
