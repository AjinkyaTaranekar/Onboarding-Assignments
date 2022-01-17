package com.nuclei.assignment.service.graphoperations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.exception.CustomException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;


/**
 * The type Graph operations test.
 */
public class GraphOperationsTest {
  
  private final GraphOperations graphOperations = new GraphOperationsImpl();
  
  /**
   * Instantiates a new Graph operations test.
   *
   * @throws CustomException the custom exception
   */
  public GraphOperationsTest() throws CustomException {
    graphOperations.createUser(new UserEntity("a", 0));
    graphOperations.createUser(new UserEntity("b", 1));
    graphOperations.createUser(new UserEntity("c", 2));
    graphOperations.createUser(new UserEntity("d", 3));
    graphOperations.createUser(new UserEntity("e", 4));
    graphOperations.createUser(new UserEntity("f", 5));
    graphOperations.createDependency(0,1);
    graphOperations.createDependency(1,3);
    graphOperations.createDependency(1,4);
    graphOperations.createDependency(2,4);
  }
  
  /**
   * Gets user by id when id is present.
   *
   * @throws CustomException the custom exception
   */
  @Test
  void getUserByIdWhenIdIsPresent() throws CustomException {
    UserEntity actualUser = new UserEntity("c", 2);
    UserEntity expectedUser = graphOperations.getUserById(2);
    
    assertEquals(expectedUser.getName(), actualUser.getName());
  }
  
  /**
   * Gets user by id when id is absent.
   */
  @Test
  void getUserByIdWhenIdIsAbsent() {
    int id = 6;
    final Exception exception = assertThrows(CustomException.class,
        () -> graphOperations.getUserById(id));
  
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_ID, id);
    final String actualMessage = exception.getMessage();
  
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Check user exist by id when id is present.
   *
   * @throws CustomException the custom exception
   */
  @Test
  void checkUserExistByIdWhenIdIsPresent() throws CustomException {
    int id = 1;
    boolean actualMessage = graphOperations.checkUserExistById(id);
    boolean expectedMessage = true;
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Check user exist by id when i dis absent.
   *
   * @throws CustomException the custom exception
   */
  @Test
  void checkUserExistByIdWhenIDisAbsent() throws CustomException {
    int id = 6;
    boolean actualMessage = graphOperations.checkUserExistById(id);
    boolean expectedMessage = false;
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Create user.
   */
  @Test
  void createUser() {
    UserEntity user = new UserEntity("f", 6);
    assertDoesNotThrow(() -> graphOperations.createUser(user));
  }
  
  /**
   * Create dependency.
   */
  @Test
  void createDependency() {
    int parentId = 4;
    int childId = 5;
    assertDoesNotThrow(() -> graphOperations.createDependency(parentId, childId));
  }
  
  /**
   * Create dependency with parent and child id is same.
   */
  @Test
  void createDependencyWithParentAndChildIdIsSame() {
    int parentId = 4;
    int childId = 4;
    final Exception exception = assertThrows(CustomException.class,
        () -> graphOperations.createDependency(parentId, childId));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.PARENT_NOT_EQUALS_CHILD,
          parentId, childId);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Creating cyclic dependency.
   */
  @Test
  void creatingCyclicDependency() {
    int parentId = 4;
    int childId = 1;
    final Exception exception = assertThrows(CustomException.class,
        () -> graphOperations.createDependency(parentId, childId));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CYCLIC_DEPENDENCY,
          parentId, childId);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Gets immediate parents.
   *
   * @throws CustomException the custom exception
   */
  @Test
  void getImmediateParents() throws CustomException {
    int id = 4;
    Set<Integer> actualList = graphOperations.getImmediateParents(id);
    Set<Integer> expectedList = new HashSet<>(List.of(1,2));
  
    assertEquals(expectedList, actualList);
  }
  
  /**
   * Gets immediate children.
   *
   * @throws CustomException the custom exception
   */
  @Test
  void getImmediateChildren() throws CustomException {
    int id = 1;
    Set<Integer> actualList = graphOperations.getImmediateChildren(id);
    Set<Integer> expectedList = new HashSet<>(List.of(3,4));
  
    assertEquals(expectedList, actualList);
  }
  
  /**
   * Gets all ancestors.
   *
   * @throws CustomException the custom exception
   */
  @Test
  void getAllAncestors() throws CustomException {
    int id = 4;
    Set<Integer> actualList = graphOperations.getAllAncestors(id);
    Set<Integer> expectedList = new HashSet<>(List.of(0,1,2));
  
    assertEquals(expectedList, actualList);
  }
  
  /**
   * Gets all descendants.
   *
   * @throws CustomException the custom exception
   */
  @Test
  void getAllDescendants() throws CustomException {
    int id = 1;
    Set<Integer> actualList = graphOperations.getAllDescendants(id);
    Set<Integer> expectedList = new HashSet<>(List.of(3,4));
  
    assertEquals(expectedList, actualList);
  }
  
  /**
   * Delete dependency.
   *
   * @throws CustomException the custom exception
   */
  @Test
  void deleteDependency() throws CustomException {
    int parentId = 8;
    int childId = 9;
    graphOperations.createUser(new UserEntity("g", parentId));
    graphOperations.createUser(new UserEntity("f", childId));
    graphOperations.createDependency(parentId, childId);
    assertDoesNotThrow(() -> graphOperations.deleteDependency(parentId, childId));
  }
  
  /**
   * Delete dependency but dependency does not exist.
   */
  @Test
  void deleteDependencyButDependencyDoesNotExist() {
    int parentId = 0;
    int childId = 4;
    final Exception exception = assertThrows(CustomException.class,
        () -> graphOperations.deleteDependency(parentId, childId));
    
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NO_DEPENDENCY,
          parentId, childId);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Delete user by id.
   *
   * @throws CustomException the custom exception
   */
  @Test
  void deleteUserById() throws CustomException {
    int id = 9;
    graphOperations.createUser(new UserEntity("g", 7));
    graphOperations.createUser(new UserEntity("h", 8));
    graphOperations.createUser(new UserEntity("i", id));
    graphOperations.createDependency(id, 8);
    graphOperations.createDependency(7, id);
    assertDoesNotThrow(() -> graphOperations.deleteUserById(id));
  }
  
}
