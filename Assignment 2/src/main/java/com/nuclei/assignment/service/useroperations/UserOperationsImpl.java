package com.nuclei.assignment.service.useroperations;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.constants.SuccessConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.diskstorage.DiskStorageOperation;
import com.nuclei.assignment.service.diskstorage.DiskStorageOperationImpl;
import com.nuclei.assignment.service.inputvalidation.InputValidation;
import com.nuclei.assignment.service.inputvalidation.InputValidationImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * UserEntity CRUD Operations implementations.
 */
public class UserOperationsImpl implements UserOperations {
  
  /**
   * The Users.
   */
  private List<UserEntity> users;
  
  /**
   * The Disk storage operation.
   */
  private final DiskStorageOperation diskStorageOperation;
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(UserOperationsImpl.class);
  
  /**
   * Instantiates a new User operations.
   */
  public UserOperationsImpl() {
    final String filePathOfUserStorage = StringConstantsUtils.USER_STORAGE;
    diskStorageOperation = new DiskStorageOperationImpl(filePathOfUserStorage);
    users = getUsersFromDisk();
  }
  
  @Override
  public List<UserEntity> getUsers() {
    return users;
  }
  
  private List<UserEntity> getUsersFromDisk() {
    try {
      users = diskStorageOperation.fetchUsersFromDisk();
    } catch (Exception exception) {
      users = new ArrayList<>();
    }
    return users;
  }
  
  @Override
  public void addUser(final UserEntity user) throws CustomException {
    final boolean userExist =
        checkIfUserExistByRollNumber(user.getRollNumber());
    if (userExist) {
      logger.error(String.format(ExceptionsConstantsUtils.ALREADY_PRESENT_ROLL_NUMBER,
          user.getRollNumber()));
      throw new CustomException(
          String.format(ExceptionsConstantsUtils.ALREADY_PRESENT_ROLL_NUMBER,
            user.getRollNumber()));
    }
    final int index = Collections.binarySearch(users, user,
        Comparator.comparing(UserEntity::getName).thenComparing(UserEntity::getRollNumber));
  
    users.add(-(index + 1), user);
    System.out.println(SuccessConstantsUtils.CREATED_USER);
  }
  
  @Override
  public UserEntity getUserByRollNumber(final int rollNumber) {
    final int index =  Collections.binarySearch(users, new UserEntity(rollNumber),
        Comparator.comparing(UserEntity::getRollNumber));
    if (index < 0) {
      return null;
    }
    return users.get(index);
  }
  
  @Override
  public boolean checkIfUserExistByRollNumber(final int rollNumber) {
    final UserEntity user = getUserByRollNumber(rollNumber);
    try {
      final InputValidation validation = new InputValidationImpl();
      validation.validateUser(user);
    } catch (Exception exception) {
      return false;
    }
    return true;
  }
  
  @Override
  public void deleteUserByRollNumber(final int rollNumber) throws CustomException {
    final UserEntity user = getUserByRollNumber(rollNumber);
    try {
      final InputValidation validation = new InputValidationImpl();
      validation.validateUser(user);
    } catch (Exception exception) {
      throw new CustomException(String.format(exception.getMessage(), rollNumber),
          exception);
    }
    users.remove(user);
    System.out.println(SuccessConstantsUtils.DELETED_USER);
  }
  
  @Override
  public void saveUsersToDisk() throws CustomException {
    diskStorageOperation.saveUsersToDisk(users);
    System.out.println(SuccessConstantsUtils.SAVE_USERS);
  }
  
  @Override
  public List<UserEntity> sortUsersBasedOnParameters(final int columnNumber,
                                               final SortingOrder sortingOrder) {
    users.sort((user, anotherUser) -> {
      int result = 0;
      switch (columnNumber) {
        case 1:
          result = user.getName().compareTo(anotherUser.getName());
          break;
        case 2:
          result = user.getRollNumber() - anotherUser.getRollNumber();
          break;
        case 3:
          result = user.getAge() - anotherUser.getAge();
          break;
        case 4:
          result = user.getAddress().compareTo(anotherUser.getAddress());
          break;
        default: break;
      }
      if (sortingOrder == SortingOrder.DESC) {
        result = -result;
      }
      return result;
    });
    return users;
  }
}