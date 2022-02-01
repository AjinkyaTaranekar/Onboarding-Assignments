package com.nuclei.example.service.inventoryservice;

import com.nuclei.example.constants.ExceptionsConstantsUtils;
import com.nuclei.example.entity.InventoryEntity;
import com.nuclei.example.exceptions.InventoryException;
import com.nuclei.example.exceptions.ValidationException;
import com.nuclei.example.repo.InventoryRepo;
import com.nuclei.example.service.taxservice.TaxService;
import com.nuclei.example.service.taxservice.TaxServiceImpl;
import com.nuclei.example.utils.Utility;
import com.nuclei.example.utils.UtilityImpl;
import com.nuclei.example.validation.Validation;
import com.nuclei.example.validation.ValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Inventory service.
 */
@Service
public class InventoryServiceImpl implements InventoryService {
  
  /**
   * The Tax service.
   */
  private final TaxService taxService;
  
  /**
   * The Validation.
   */
  private final Validation validation;
  /**
   * The Utility.
   */
  private final Utility utility;
  /**
   * The Inventory repo.
   */
  @Autowired
  private InventoryRepo inventoryRepo;
  
  /**
   * Instantiates a new Inventory service.
   */
  @Autowired
  public InventoryServiceImpl () {
    this.taxService = new TaxServiceImpl();
    this.validation = new ValidationImpl();
    this.utility = new UtilityImpl();
  }
  
  @Override
  public InventoryEntity createInventory (InventoryEntity inventoryEntity) throws ValidationException {
    validateInventory(inventoryEntity);
    setTaxForTheNewInventory(inventoryEntity);
    inventoryRepo.save(inventoryEntity);
    return inventoryEntity;
  }
  
  @Override
  public InventoryEntity getInventoryById (String id) throws ValidationException, InventoryException {
    validation.validateId(id);
    return inventoryRepo.findById(id).orElseThrow(() -> new InventoryException(
        String.format(ExceptionsConstantsUtils.ID_NOT_FOUND, id),
        ExceptionsConstantsUtils.NOT_FOUND));
  }
  
  @Override
  public Iterable<InventoryEntity> getAllInventory () {
    return inventoryRepo.findAll();
  }
  
  @Override
  public List<InventoryEntity> searchInInventory (final String query) {
    return inventoryRepo.searchInInventory(query);
  }
  
  @Override
  public InventoryEntity updateInventory (String id, InventoryEntity inventoryEntity) throws ValidationException, InventoryException {
    InventoryEntity existingInventory = getInventoryById(id);
    utility.copyNotNullProperties(inventoryEntity, existingInventory);
    return inventoryRepo.save(existingInventory);
  }
  
  @Override
  public void deleteInventoryById (String id) throws InventoryException, ValidationException {
    InventoryEntity inventoryEntity = getInventoryById(id);
    inventoryRepo.delete(inventoryEntity);
  }
  
  private void validateInventory (InventoryEntity inventoryEntity) throws ValidationException {
    validation.validatePrice(inventoryEntity.getPrice());
    validation.validateQuantity(inventoryEntity.getQuantity());
    validation.validateName(inventoryEntity.getName());
  }
  
  private void setTaxForTheNewInventory (final InventoryEntity inventoryEntity) {
    switch (inventoryEntity.getType()) {
      case RAW:
        inventoryEntity.setSalesTax(taxService.getTaxForRawInventory(inventoryEntity.getPrice()));
        break;
      case MANUFACTURED:
        inventoryEntity.setSalesTax(taxService.getTaxForManufacturedInventory(inventoryEntity.getPrice()));
        break;
      case IMPORTED:
        inventoryEntity.setSalesTax(taxService.getTaxForImportedInventory(inventoryEntity.getPrice()));
        break;
      default:
    }
    inventoryEntity.setFinalPrice(inventoryEntity.getSalesTax() + inventoryEntity.getPrice());
  }
}
