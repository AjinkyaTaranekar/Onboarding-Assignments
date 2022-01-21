package com.nuclei.assignment.service.diskstorage;

import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.exception.CustomException;

import java.util.List;

/**
 * DiskOperation Interface.
 */
public interface DiskStorageOperation {
  
  /**
   * Save user list to disk.
   *
   * @param users the users
   * @throws CustomException the custom exception
   */
  void saveUsersToDisk(List<UserEntity> users) throws CustomException;
  
  /**
   * Get user list from disk.
   *
   * @return the list
   * @throws CustomException the custom exception
   */
  List<UserEntity> fetchUsersFromDisk() throws CustomException;
}
