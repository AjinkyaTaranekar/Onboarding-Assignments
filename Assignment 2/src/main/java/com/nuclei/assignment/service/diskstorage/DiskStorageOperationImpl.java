package com.nuclei.assignment.service.diskstorage;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.SuccessConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.exception.CustomException;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Disk Storage Operation implementation.
 */
public class DiskStorageOperationImpl implements DiskStorageOperation {
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(DiskStorageOperationImpl.class);
  
  /**
   * The File path Of UserStorage.
   */
  private final String filePathOfUserStorage;
  
  /**
   * Instantiates a new Disk storage operation.
   *
   * @param filePathOfUserStorage the file path
   */
  public DiskStorageOperationImpl(String filePathOfUserStorage) {
    this.filePathOfUserStorage = filePathOfUserStorage;
  }
  
  @Override
  public void saveUsersToDisk(final List<UserEntity> users) throws CustomException {
    try (
        OutputStream file = Files.newOutputStream(Paths.get(filePathOfUserStorage));
        ObjectOutputStream writer = new ObjectOutputStream(file)
    ) {
      for (final UserEntity user : users) {
        writer.writeObject(user);
      }
    } catch (IOException exception) {
      logger.error(ExceptionsConstantsUtils.FAILED_TO_WRITE_STORAGE,
          exception);
      throw new CustomException(ExceptionsConstantsUtils.FAILED_TO_WRITE_STORAGE,
          exception);
    }
  }
  
  @Override
  public List<UserEntity> fetchUsersFromDisk() throws CustomException {
    final List<UserEntity> users = new ArrayList<>();
    try (
        InputStream file = Files.newInputStream(Paths.get(filePathOfUserStorage));
        ObjectInputStream reader = new ObjectInputStream(file)
    ) {
      try {
        Object obj;
        do {
          obj = reader.readObject();
          users.add((UserEntity) obj);
        } while (obj != null);
      } catch (EOFException exception) {
        logger.info(ExceptionsConstantsUtils.END_OF_FILE_REACHED);
      }
    } catch (IOException | ClassNotFoundException | ClassCastException exception) {
      logger.error(ExceptionsConstantsUtils.FAILED_TO_READ_STORAGE);
      saveUsersToDisk(new ArrayList<>(0));
      throw new CustomException(ExceptionsConstantsUtils.FAILED_TO_READ_STORAGE, exception);
    }
    logger.info(SuccessConstantsUtils.FETCH_SAVED_USERS);
    System.out.println(SuccessConstantsUtils.FETCH_SAVED_USERS);
    return users;
  }
}
