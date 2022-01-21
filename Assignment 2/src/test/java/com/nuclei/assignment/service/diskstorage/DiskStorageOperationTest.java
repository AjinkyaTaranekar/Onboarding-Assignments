package com.nuclei.assignment.service.diskstorage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.exception.CustomException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


/**
 * The type Disk storage operation test.
 */
public class DiskStorageOperationTest {
  
  private final String filePath = "./user_test.ser";
  
  /**
   * Save users to disk.
   */
  @Test
  public void saveUsersToDisk() {
    List<UserEntity> users = new ArrayList<>();
    users.add(new UserEntity(1));
    users.add(new UserEntity(2));
    users.add(new UserEntity(3));
    
    assertDoesNotThrow(() -> new DiskStorageOperationImpl(filePath).saveUsersToDisk(users));
  }
  
  /**
   * Fetch users from disk.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void fetchUsersFromDisk() throws CustomException {
    List<UserEntity> users = new DiskStorageOperationImpl(filePath).fetchUsersFromDisk();
    assertEquals(users.size(), 3);
  }
  
}
