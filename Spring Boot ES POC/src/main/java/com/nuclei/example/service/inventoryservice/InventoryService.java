package com.nuclei.example.service.inventoryservice;

import com.nuclei.example.entity.InventoryEntity;
import com.nuclei.example.exceptions.InventoryException;
import com.nuclei.example.exceptions.ValidationException;

import java.util.List;

/**
 * The interface Inventory service.
 */
public interface InventoryService {
  
  /**
   * Create inventory inventory entity.
   *
   * @param inventoryEntity the inventory entity
   *
   * @return the inventory entity
   *
   * @throws ValidationException the validation exception
   */
  InventoryEntity createInventory (InventoryEntity inventoryEntity) throws ValidationException;
  
  /**
   * Gets inventory by id.
   *
   * @param id the id
   *
   * @return the inventory by id
   *
   * @throws ValidationException the validation exception
   * @throws InventoryException  the inventory exception
   */
  InventoryEntity getInventoryById (String id) throws ValidationException, InventoryException;
  
  /**
   * Gets all inventory.
   *
   * @return the all inventory
   */
  Iterable<InventoryEntity> getAllInventory ();
  
  List<InventoryEntity> searchInInventory (String query);
  
  /**
   * Update inventory inventory entity.
   *
   * @param id              the id
   * @param inventoryEntity the inventory entity
   *
   * @return the inventory entity
   *
   * @throws ValidationException the validation exception
   * @throws InventoryException  the inventory exception
   */
  InventoryEntity updateInventory (String id, InventoryEntity inventoryEntity) throws ValidationException, InventoryException;
  
  /**
   * Delete inventory by id.
   *
   * @param id the id
   *
   * @throws InventoryException  the inventory exception
   * @throws ValidationException the validation exception
   */
  void deleteInventoryById (String id) throws InventoryException, ValidationException;
  
}
