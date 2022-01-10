package com.nuclei.assignment.service.diskstorage;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.constants.SuccessConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.exception.CustomException;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Disk Storage Operation implementation.
 */
public class DiskStorageOperationImpl implements DiskStorageOperation {
  
  @Override
  public void saveUsersToDisk(final List<UserEntity> users) throws CustomException {
    try (OutputStream file =
           Files.newOutputStream(Paths.get(StringConstantsUtils.USER_STORAGE))) {
      try (ObjectOutputStream writer = new ObjectOutputStream(file)) {
        writer.writeObject(users);
      } catch (Exception exception) {
        throw new CustomException(ExceptionsConstantsUtils.FAILED_TO_WRITE_STORAGE,
          exception);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.FAILED_TO_WRITE_STORAGE,
          exception);
    }
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public List<UserEntity> fetchUsersFromDisk() throws CustomException {
    final List<UserEntity> users;
    try (InputStream file =
           Files.newInputStream(Paths.get(StringConstantsUtils.USER_STORAGE))) {
      try (ObjectInputStream reader = new ObjectInputStream(file)) {
        users = (ArrayList<UserEntity>) reader.readObject();
      } catch (Exception exception) {
        throw new CustomException(ExceptionsConstantsUtils.FAILED_TO_READ_STORAGE, exception);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.FAILED_TO_READ_STORAGE, exception);
    }
    System.out.println(SuccessConstantsUtils.FETCH_SAVED_USERS);
    return users;
  }
}
