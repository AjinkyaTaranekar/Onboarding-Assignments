package com.nuclei.assignment.service.graphoperations;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.SuccessConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.exception.CustomException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Graph operations Implementation.
 */
public class GraphOperationsImpl implements GraphOperations {
  /**
   * The Users.
   */
  private final List<UserEntity> users;
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(GraphOperationsImpl.class);
  
  /**
   * Graph operations Constructor.
   */
  public GraphOperationsImpl() {
    users = new ArrayList<>();
  }
  
  private Integer getIndexInList(final int id) {
    return Collections.binarySearch(users, new UserEntity(id),
      Comparator.comparing(UserEntity::getId));
  }

  @Override
  public UserEntity getUserById(final int id) throws CustomException {
    final int index = getIndexInList(id);
    if (index < 0) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_ID, id));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_ID,
          id));
    }
    final UserEntity user = users.get(index);
    logger.info(String.format(SuccessConstantsUtils.USER_INFO, user));
    return user;
  }
  
  @Override
  public boolean checkUserExistById(final int id) {
    final int index = getIndexInList(id);
    return index >= 0;
  }
  
  @Override
  public void createUser(final UserEntity user) throws CustomException {
    final int index = getIndexInList(user.getId());
    if (index >= 0) {
      logger.error(String.format(ExceptionsConstantsUtils.ID_ALREADY_EXISTS,
          user.getId()));
      throw new CustomException(String.format(ExceptionsConstantsUtils.ID_ALREADY_EXISTS,
          user.getId()));
    }
    users.add(-(index + 1), user);
    logger.info(String.format(SuccessConstantsUtils.USER_INFO, user));
    System.out.println(SuccessConstantsUtils.CREATED_USER);
  }
  
  @Override
  public void createDependency(final int parentId, final int childId) throws CustomException {
  
    if (parentId == childId) {
      logger.error(String.format(ExceptionsConstantsUtils.PARENT_NOT_EQUALS_CHILD,
          parentId, childId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.PARENT_NOT_EQUALS_CHILD,
          parentId, childId));
    }
    
    if (checkCyclicDependency(parentId, childId)) {
      logger.error(String.format(ExceptionsConstantsUtils.CYCLIC_DEPENDENCY,
          parentId, childId));
      throw new CustomException(String.format(
          ExceptionsConstantsUtils.CYCLIC_DEPENDENCY, parentId, childId));
    }
  
    final UserEntity parentUser = getUserById(parentId);
    final UserEntity childUser = getUserById(childId);
  
    final Set<Integer> parents = childUser.getParents();
    parents.add(parentId);
    childUser.setParents(parents);
  
    final Set<Integer> children = parentUser.getChildren();
    children.add(childId);
    parentUser.setChildren(children);
    System.out.println(SuccessConstantsUtils.CREATED_DEPENDENCY);
  
  }
  
  private boolean checkCyclicDependency(final int parentId, final int childId)
      throws CustomException {
    final UserEntity parentUser = getUserById(parentId);
    final Set<Integer> users = new HashSet<>();
    final Queue<UserEntity> queue = new PriorityQueue<>();
    final Set<Integer> visitedIds = new HashSet<>();
    queue.add(parentUser);
    visitedIds.add(parentId);
    while (!queue.isEmpty()) {
      final UserEntity currentUser = queue.poll();
      users.addAll(currentUser.getChildren());
      users.addAll(currentUser.getParents());
      for (final Integer userId : users) {
        if (visitedIds.contains(userId)) {
          continue;
        }
        if (userId.equals(childId)) {
          return true;
        }
        visitedIds.add(userId);
        final UserEntity tempUser = getUserById(userId);
        queue.add(tempUser);
        users.add(userId);
      }
    }
    return false;
  }
  
  @Override
  public Set<Integer> getImmediateParents(final int id) throws CustomException {
    final UserEntity user = getUserById(id);
    return user.getParents();
  }
  
  @Override
  public Set<Integer> getImmediateChildren(final int id) throws CustomException {
    final UserEntity user = getUserById(id);
    return user.getChildren();
  }
  
  @Override
  public Set<Integer> getAllAncestors(final int id) throws CustomException {
    final UserEntity user = getUserById(id);
    final Set<Integer> ancestors = new HashSet<>();
    final Queue<UserEntity> queue = new PriorityQueue<>();
    final Map<Integer, Boolean> visited = new ConcurrentHashMap<>();
    queue.add(user);
    visited.put(id, true);
    while (!queue.isEmpty()) {
      final UserEntity currentUser = queue.poll();
      for (final Integer ancestor : currentUser.getParents()) {
        if (visited.getOrDefault(ancestor, false)) {
          continue;
        }
        visited.put(ancestor, true);
        final UserEntity tempUser = getUserById(ancestor);
        queue.add(tempUser);
        ancestors.add(ancestor);
      }
    }
    return ancestors;
  }
  
  @Override
  public Set<Integer> getAllDescendants(final int id) throws CustomException {
    final UserEntity user = getUserById(id);
    final Set<Integer> children = new HashSet<>();
    final Queue<UserEntity> queue = new PriorityQueue<>();
    final Map<Integer, Boolean> visited = new ConcurrentHashMap<>();
    queue.add(user);
    visited.put(id, true);
    while (!queue.isEmpty()) {
      final UserEntity currentUser = queue.poll();
      for (final Integer child : currentUser.getChildren()) {
        if (visited.getOrDefault(child, false)) {
          continue;
        }
        visited.put(child, true);
        final UserEntity tempUser = getUserById(child);
        queue.add(tempUser);
        children.add(child);
      }
    }
    return children;
  }
  
  @Override
  public void deleteUserById(final int id) throws CustomException {
    final UserEntity user = getUserById(id);
    for (final Integer parent : user.getParents()) {
      deleteDependency(parent, id);
    }
  
    for (final Integer child : user.getChildren()) {
      deleteDependency(id, child);
    }
    users.remove(user);
    System.out.println(SuccessConstantsUtils.DELETED_USER);
  }
  
  @Override
  public void deleteDependency(final int parentId, final int childId) throws CustomException {
    final UserEntity parentUser = getUserById(parentId);
  
    final Set<Integer> children = parentUser.getChildren();
    if (!children.contains(childId)) {
      logger.error(String.format(ExceptionsConstantsUtils.NO_DEPENDENCY,
          parentId, childId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.NO_DEPENDENCY,
          parentId, childId));
    }
    children.remove(childId);
    parentUser.setChildren(children);
  
    final UserEntity childUser = getUserById(childId);
    final Set<Integer> parents = childUser.getParents();
    parents.remove(parentId);
    childUser.setParents(parents);
    System.out.println(SuccessConstantsUtils.DELETED_DEPENDENCY);
  }
}
