package com.nuclei.assignment.service.menuoptions;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.constants.SuccessConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.inputvalidation.InputValidation;
import com.nuclei.assignment.service.inputvalidation.InputValidationImpl;
import com.nuclei.assignment.service.useroperations.UserOperations;
import com.nuclei.assignment.service.useroperations.UserOperationsImpl;

import java.util.List;
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
   * The User operations.
   */
  private final UserOperations userOperations;
  /**
   * The Validation.
   */
  private final InputValidation validation;
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(MenuOptionsImpl.class);
  
  /**
   * Menu Option Constructor.
   */
  public MenuOptionsImpl() {
    userOperations = new UserOperationsImpl();
    validation = new InputValidationImpl();
  }
  
  @Override
  public void menuInterface() {
    logger.info("New user session started");
    try (Scanner scanner = new Scanner(System.in)) {
      String addMoreUsers = StringConstantsUtils.CONFIRMATION;
      while (addMoreUsers.equals(StringConstantsUtils.CONFIRMATION)) {
        for (final String info : StringConstantsUtils.MENU_DETAILS_INFO) {
          System.out.println(info);
        }
        try {
          final int choice = chooseMenuOption(scanner);
          final int exitOption = 5;
          if (choice == exitOption) {
            break;
          }
        } catch (Exception exception) {
          System.out.println(exception.getMessage());
        }
        System.out.println(StringConstantsUtils.DIVIDER);
        System.out.print(StringConstantsUtils.CONTINUE_MORE_OPERATIONS);
        addMoreUsers = scanner.nextLine().toLowerCase(Locale.ROOT);
      }
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
    logger.info("User session ended");
  }
  
  private int chooseMenuOption(final Scanner scanner) throws CustomException {
    final int menuOption = Integer.parseInt(scanner.nextLine());
    switch (menuOption) {
      case 1:
        addUserOption(scanner);
        break;
      case 2:
        displayUsersOption(scanner);
        break;
      case 3:
        deleteUserOption(scanner);
        break;
      case 4:
        saveUsersOption();
        break;
      case 5:
        exitOption(scanner);
        break;
      default: throw new CustomException(ExceptionsConstantsUtils.INVALID_INPUT);
    }
    return menuOption;
  }
  
  private void addUserOption(final Scanner scanner) throws CustomException {
    final UserEntity user = createUser(scanner);
    userOperations.addUser(user);
  }
  
  private UserEntity createUser(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_USER_ROLL_NUMBER);
    final int rollNumber = validation.validateRollNumber(scanner.nextLine());
    
    System.out.println(StringConstantsUtils.ADD_USER_NAME);
    final String name = validation.validateName(scanner.nextLine());
    
    System.out.println(StringConstantsUtils.ADD_USER_AGE);
    final int age = validation.validateAge(scanner.nextLine());
    
    System.out.println(StringConstantsUtils.ADD_USER_ADDRESS);
    final String address = validation.validateAddress(scanner.nextLine());
    
    System.out.println(StringConstantsUtils.ADD_USER_COURSES);
    
    for (final String note : StringConstantsUtils.COURSES_NOTE) {
      System.out.println(note);
    }
    int coursesCount = 1;
    for (final Courses course : Courses.values()) {
      System.out.printf(StringConstantsUtils.LIST_FORMAT, coursesCount, course);
      coursesCount++;
    }
    final Set<Courses> courses =
        validation.validateCourses(scanner.nextLine());
    return new UserEntity(name, age, address, rollNumber, courses);
  }
  
  private void displayUsersOption(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.SORT_BY_COLUMN);
    int fieldIndex = 1;
    for (final String fields : StringConstantsUtils.USER_FIELDS) {
      if (fieldIndex == StringConstantsUtils.USER_FIELDS.length) {
        continue;
      }
      System.out.printf(StringConstantsUtils.LIST_FORMAT, fieldIndex, fields);
      fieldIndex++;
    }
    final int columnNumber =
        validation.validateColumnNumberForSorting(scanner.nextLine());
    System.out.println(StringConstantsUtils.SORT_BY_ORDER);
    final SortingOrder sortOrder =
        validation.validateOrderOfSorting(scanner.nextLine());
    final List<UserEntity> users =
        userOperations.sortUsersBasedOnParameters(columnNumber, sortOrder);
    System.out.println(SuccessConstantsUtils.DISPLAY_USERS);
    System.out.println(StringConstantsUtils.DIVIDER);
    System.out.format(StringConstantsUtils.LEFT_ALIGN_FORMAT,
        StringConstantsUtils.USER_FIELDS[0], StringConstantsUtils.USER_FIELDS[1],
        StringConstantsUtils.USER_FIELDS[2], StringConstantsUtils.USER_FIELDS[3],
        StringConstantsUtils.USER_FIELDS[4]);
    System.out.println(StringConstantsUtils.DIVIDER);
    for (final UserEntity user : users) {
      System.out.format(StringConstantsUtils.LEFT_ALIGN_FORMAT, user.getName(),
          user.getRollNumber(), user.getAge(), user.getAddress(),
          user.getCourses());
    }
  }
  
  private void deleteUserOption(final Scanner scanner) {
    try {
      System.out.print(StringConstantsUtils.DELETE_USER_ROLL_NUMBER);
      final int rollNumber = validation.validateRollNumber(scanner.nextLine());
      userOperations.deleteUserByRollNumber(rollNumber);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }
  
  private void saveUsersOption() throws CustomException {
    userOperations.saveUsersToDisk();
  }
  
  private void exitOption(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.DIVIDER);
    System.out.println(StringConstantsUtils.SAVE_USERS);
    final String saveUsers = scanner.nextLine().toLowerCase(Locale.ROOT);
    if (saveUsers.equals(StringConstantsUtils.CONFIRMATION)) {
      saveUsersOption();
    }
  }
}
