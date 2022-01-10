package com.nuclei.assignment.service.useroperations;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.SuccessConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.diskstorage.DiskStorageOperation;
import com.nuclei.assignment.service.diskstorage.DiskStorageOperationImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * UserEntity CRUD Operations implementations.
 */
public class UserOperationsImpl implements UserOperations {
  
  /**
   * The Users.
   */
  public List<UserEntity> users;
  
  /**
   * The Disk storage operation.
   */
  private final DiskStorageOperation diskStorageOperation;
  
  /**
   * UserEntity Operations Constructor.
   */
  public UserOperationsImpl() {
    diskStorageOperation = new DiskStorageOperationImpl();
    users = getUsersFromDisk();
  }
  
  public List<UserEntity> getUsers(){
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
  public void addUser(final UserEntity user) {
    final int index = Collections.binarySearch(users, user,
        Comparator.comparing(UserEntity::getName).thenComparing(UserEntity::getRollNumber));
    users.add(-(index + 1), user);
    System.out.println(SuccessConstantsUtils.CREATED_USER);
  }
  
  @Override
  public UserEntity getUserByRollNumber(final int rollNumber) throws CustomException {
    final int index =  Collections.binarySearch(users, new UserEntity(rollNumber),
        Comparator.comparing(UserEntity::getRollNumber));
    if (index < 0) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_ROLL_NUMBER);
    }
    return users.get(index);
  }
  
  @Override
  public boolean checkIfUserExistByRollNumber(final int rollNumber) {
    try {
      getUserByRollNumber(rollNumber);
    } catch (Exception exception) {
      return false;
    }
    return true;
  }
  
  @Override
  public void deleteUserByRollNumber(final int rollNumber) throws CustomException {
    final UserEntity user = getUserByRollNumber(rollNumber);
    if (user == null) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_ROLL_NUMBER);
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
