package com.nuclei.assignment.service.menuoptions;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.graphoperations.GraphOperations;
import com.nuclei.assignment.service.graphoperations.GraphOperationsImpl;
import com.nuclei.assignment.service.inputvalidation.InputValidation;
import com.nuclei.assignment.service.inputvalidation.InputValidationImpl;

import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Menu Option implementation for different types of access.
 */
public class MenuOptionsImpl implements MenuOptions {
  /**
   * The Graph operations.
   */
  private final GraphOperations graphOperations;
  
  /**
   * The Validation.
   */
  private final InputValidation validation;
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(MenuOptionsImpl.class);
  
  /**
   * Menu Option constructor.
   */
  public MenuOptionsImpl() {
    graphOperations = new GraphOperationsImpl();
    validation = new InputValidationImpl();
  }
  
  @Override
  public void menuInterface() {
    try (Scanner scanner = new Scanner(System.in)) {
      String addMoreUsers = StringConstantsUtils.CONFIRMATION;
      while (addMoreUsers.equals(StringConstantsUtils.CONFIRMATION)) {
        for (final String info : StringConstantsUtils.MENU_DETAILS_INFO) {
          System.out.println(info);
        }
        try {
          chooseMenuOption(scanner);
        } catch (Exception exception) {
          System.out.println(exception.getMessage());
        } finally {
          System.out.println(StringConstantsUtils.DIVIDER);
          System.out.print(StringConstantsUtils.CONTINUE_MORE_OPERATIONS);
          addMoreUsers = scanner.nextLine().toLowerCase(Locale.ROOT);
        }
      }
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }
  
  // Suppressing warning of
  // The method 'chooseMenuOption(Scanner)' has a cyclomatic complexity of 10.
  // Since the menu has 8 options, so it can't be reduced further.
  @SuppressWarnings("PMD.CyclomaticComplexity")
  private void chooseMenuOption(final Scanner scanner) throws CustomException {
    final int menuOption = Integer.parseInt(scanner.nextLine());
    switch (menuOption) {
      case 1:
        getImmediateParents(scanner);
        break;
      case 2:
        getImmediateChildren(scanner);
        break;
      case 3:
        getAllAncestors(scanner);
        break;
      case 4:
        getAllDescendants(scanner);
        break;
      case 5:
        deleteDependency(scanner);
        break;
      case 6:
        deleteUserById(scanner);
        break;
      case 7:
        createDependency(scanner);
        break;
      case 8:
        addUserOption(scanner);
        break;
      default:
        logger.error(String.format(ExceptionsConstantsUtils.INVALID_INPUT, menuOption));
        throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_INPUT,
            menuOption));
    }
  }
  
  private void addUserOption(final Scanner scanner) throws CustomException {
    createUser(scanner);
  }
  
  private void createUser(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_USER_ID);
    final int id = validation.validateId(scanner.nextLine());
    if (graphOperations.checkUserExistById(id)) {
      logger.error(String.format(ExceptionsConstantsUtils.ID_ALREADY_EXISTS,
          id));
      throw new CustomException(String.format(ExceptionsConstantsUtils.ID_ALREADY_EXISTS,
          id));
    }
    
    System.out.println(StringConstantsUtils.ADD_USER_NAME);
    final String name = validation.validateName(scanner.nextLine());
    
    final UserEntity user = new UserEntity(name, id);
    graphOperations.createUser(user);
  }
  
  private void createDependency(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_PARENT_ID);
    final int parentId = validation.validateId(scanner.nextLine());
    if (!graphOperations.checkUserExistById(parentId)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_PARENT_ID,
          parentId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_PARENT_ID,
          parentId));
    }
    
    System.out.println(StringConstantsUtils.ADD_CHILD_ID);
    final int childId = validation.validateId(scanner.nextLine());
    if (!graphOperations.checkUserExistById(childId)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_CHILD_ID,
          childId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_CHILD_ID,
          childId));
    }
    graphOperations.createDependency(parentId, childId);
  }
  
  private void getImmediateParents(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_USER_ID);
    final int id = validation.validateId(scanner.nextLine());
    final Set<Integer> parents = graphOperations.getImmediateParents(id);
    System.out.println(parents);
  }
  
  private void getImmediateChildren(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_USER_ID);
    final int id = validation.validateId(scanner.nextLine());
    final Set<Integer> children = graphOperations.getImmediateChildren(id);
    System.out.println(children);
  }
  
  private void getAllAncestors(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_USER_ID);
    final int id = validation.validateId(scanner.nextLine());
    final Set<Integer> ancestors = graphOperations.getAllAncestors(id);
    System.out.println(ancestors);
  }
  
  private void getAllDescendants(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_USER_ID);
    final int id = validation.validateId(scanner.nextLine());
    final Set<Integer> descendants = graphOperations.getAllDescendants(id);
    System.out.println(descendants);
  }
  
  private void deleteUserById(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_USER_ID);
    final int id = validation.validateId(scanner.nextLine());
    graphOperations.deleteUserById(id);
  }
  
  private void deleteDependency(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_PARENT_ID);
    final int parentId = validation.validateId(scanner.nextLine());
    if (!graphOperations.checkUserExistById(parentId)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_PARENT_ID,
          parentId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_PARENT_ID,
          parentId));
    }
  
    System.out.println(StringConstantsUtils.ADD_CHILD_ID);
    final int childId = validation.validateId(scanner.nextLine());
    if (!graphOperations.checkUserExistById(childId)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_CHILD_ID,
          childId));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_CHILD_ID,
          childId));
    }
    graphOperations.deleteDependency(parentId, childId);
  }
  
}
