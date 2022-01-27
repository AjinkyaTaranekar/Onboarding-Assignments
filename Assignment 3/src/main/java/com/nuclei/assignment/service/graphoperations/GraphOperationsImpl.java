package com.nuclei.assignment.service.graphoperations;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.SuccessConstantsUtils;
import com.nuclei.assignment.entity.NodeEntity;
import com.nuclei.assignment.exception.CustomException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Graph operations Implementation.
 */
public class GraphOperationsImpl implements GraphOperations {
  /**
   * The Nodes.
   */
  private final Map<Integer, NodeEntity> nodes;
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(GraphOperationsImpl.class);
  
  /**
   * Graph operations Constructor.
   */
  public GraphOperationsImpl() {
    nodes = new HashMap<>();
  }
  
  @Override
  public Map<Integer, NodeEntity> getAllNodes() {
    return nodes;
  }
  
  @Override
  public NodeEntity getNodeById(final int id) throws CustomException {
    final NodeEntity node = nodes.get(id);
    if (Objects.isNull(node)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_ID, id));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_ID,
          id));
    }
    return node;
  }
  
  @Override
  public boolean checkNodeExistById(final int id) {
    final NodeEntity node = nodes.get(id);
    return !Objects.isNull(node);
  }
  
  @Override
  public void createNode(final NodeEntity node) throws CustomException {
    if (checkNodeExistById(node.getId())) {
      logger.error(String.format(ExceptionsConstantsUtils.ID_ALREADY_EXISTS,
          node.getId()));
      throw new CustomException(String.format(ExceptionsConstantsUtils.ID_ALREADY_EXISTS,
          node.getId()));
    }
    nodes.put(node.getId(), node);
    System.out.println(SuccessConstantsUtils.CREATED_NODE);
  }
  
  @Override
  public void createDependency(final int parentId, final int childId) throws CustomException {
    checkParentAndChildIdPresentInData(parentId, childId);
    if (checkCyclicDependency(parentId, childId)) {
      logger.error(String.format(ExceptionsConstantsUtils.CYCLIC_DEPENDENCY,
          parentId, childId));
      throw new CustomException(String.format(
          ExceptionsConstantsUtils.CYCLIC_DEPENDENCY, parentId, childId));
    }
  
    final NodeEntity parentNode = getNodeById(parentId);
    final NodeEntity childNode = getNodeById(childId);
  
    final Set<Integer> parents = childNode.getParents();
    parents.add(parentId);
    childNode.setParents(parents);
  
    final Set<Integer> children = parentNode.getChildren();
    children.add(childId);
    parentNode.setChildren(children);
    System.out.println(SuccessConstantsUtils.CREATED_DEPENDENCY);
  
  }
  
  private boolean checkCyclicDependency(final int parentId, final int childId)
      throws CustomException {
    final NodeEntity parentNode = getNodeById(parentId);
    final Queue<NodeEntity> queue = new PriorityQueue<>();
    final Set<Integer> visitedIds = new HashSet<>();
    queue.add(parentNode);
    visitedIds.add(parentId);
    while (!queue.isEmpty()) {
      final NodeEntity currentNode = queue.poll();
      for (final Integer nodeId : currentNode.getParents()) {
        if (visitedIds.contains(nodeId)) {
          continue;
        }
        if (nodeId.equals(childId)) {
          return true;
        }
        visitedIds.add(nodeId);
        final NodeEntity tempNode = getNodeById(nodeId);
        queue.add(tempNode);
      }
    }
    return false;
  }
  
  @Override
  public Set<Integer> getImmediateParents(final int id) throws CustomException {
    final NodeEntity node = getNodeById(id);
    return node.getParents();
  }
  
  @Override
  public Set<Integer> getImmediateChildren(final int id) throws CustomException {
    final NodeEntity node = getNodeById(id);
    return node.getChildren();
  }
  
  @Override
  public Set<Integer> getAllAncestors(final int id) throws CustomException {
    final NodeEntity node = getNodeById(id);
    final Set<Integer> ancestors = new HashSet<>();
    final Queue<NodeEntity> queue = new PriorityQueue<>();
    final Set<Integer> visited = new HashSet<>();
    queue.add(node);
    visited.add(id);
    while (!queue.isEmpty()) {
      final NodeEntity currentNode = queue.poll();
      for (final Integer ancestor : currentNode.getParents()) {
        if (visited.contains(ancestor)) {
          continue;
        }
        visited.add(ancestor);
        final NodeEntity ancestorNode = getNodeById(ancestor);
        queue.add(ancestorNode);
        ancestors.add(ancestor);
      }
    }
    return ancestors;
  }
  
  @Override
  public Set<Integer> getAllDescendants(final int id) throws CustomException {
    final NodeEntity node = getNodeById(id);
    final Set<Integer> descendants = new HashSet<>();
    final Queue<NodeEntity> queue = new PriorityQueue<>();
    final Set<Integer> visited = new HashSet<>();
    queue.add(node);
    visited.add(id);
    while (!queue.isEmpty()) {
      final NodeEntity currentNode = queue.poll();
      for (final Integer descendant : currentNode.getChildren()) {
        if (visited.contains(descendant)) {
          continue;
        }
        visited.add(descendant);
        final NodeEntity descendantNode = getNodeById(descendant);
        queue.add(descendantNode);
        descendants.add(descendant);
      }
    }
    return descendants;
  }
  
  @Override
  public void deleteNodeById(final int id) throws CustomException {
    final NodeEntity node = getNodeById(id);
    for (final Integer parent : node.getParents()) {
      deleteDependency(parent, id);
    }
    for (final Integer child : node.getChildren()) {
      deleteDependency(id, child);
    }
    nodes.remove(id);
    System.out.println(SuccessConstantsUtils.DELETED_NODE);
  }
  
  @Override
  public void deleteDependency(final int parentId, final int childId) throws CustomException {
    checkParentAndChildIdPresentInData(parentId, childId);
    final NodeEntity parentNode = getNodeById(parentId);
    final Set<Integer> children = parentNode.getChildren();
    if (!children.contains(childId)) {
      logger.error(String.format(ExceptionsConstantsUtils.NO_DEPENDENCY,
          parentId, childId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.NO_DEPENDENCY,
          parentId, childId));
    }
    children.remove(childId);
    parentNode.setChildren(children);
  
    final NodeEntity childNode = getNodeById(childId);
    final Set<Integer> parents = childNode.getParents();
    parents.remove(parentId);
    childNode.setParents(parents);
    System.out.println(SuccessConstantsUtils.DELETED_DEPENDENCY);
  }
  
  private void checkParentAndChildIdPresentInData(int parentId, int childId)
      throws CustomException {
    if (!checkNodeExistById(parentId)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_PARENT_ID,
          parentId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_PARENT_ID,
          parentId));
    }
    if (!checkNodeExistById(childId)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_CHILD_ID,
          childId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_CHILD_ID,
          childId));
    }
  
    if (parentId == childId) {
      logger.error(String.format(ExceptionsConstantsUtils.PARENT_NOT_EQUALS_CHILD,
          parentId, childId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.PARENT_NOT_EQUALS_CHILD,
          parentId, childId));
    }
  }
}
