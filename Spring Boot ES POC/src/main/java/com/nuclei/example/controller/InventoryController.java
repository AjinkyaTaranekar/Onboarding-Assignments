package com.nuclei.example.controller;


import com.nuclei.example.dto.CreateUpdateInventoryDTO;
import com.nuclei.example.dto.InventoryDTO;
import com.nuclei.example.entity.InventoryEntity;
import com.nuclei.example.exceptions.InventoryException;
import com.nuclei.example.exceptions.ValidationException;
import com.nuclei.example.mapper.InventoryMapper;
import com.nuclei.example.service.inventoryservice.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Inventory controller.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {
  private final InventoryService inventoryService;
  private final InventoryMapper inventoryMapper;
  
  /**
   * Create inventory response entity.
   *
   * @param createUpdateInventoryDTO the create update inventory dto
   *
   * @return the response entity
   *
   * @throws ValidationException the validation exception
   */
  @PostMapping
  public ResponseEntity<InventoryDTO> createInventory (@RequestBody CreateUpdateInventoryDTO createUpdateInventoryDTO) throws ValidationException {
    InventoryEntity inventoryEntity = inventoryMapper.toEntity(createUpdateInventoryDTO);
    inventoryEntity = inventoryService.createInventory(inventoryEntity);
    return ResponseEntity.ok(
        inventoryMapper.toDto(inventoryEntity)
    );
  }
  
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
  @GetMapping("/{id}")
  public ResponseEntity<InventoryDTO> getInventoryById (@PathVariable String id) throws ValidationException, InventoryException {
    InventoryEntity inventoryEntity = inventoryService.getInventoryById(id);
    return ResponseEntity.ok(
        inventoryMapper.toDto(inventoryEntity)
    );
  }
  
  /**
   * Gets all inventory.
   *
   * @return the all inventory
   */
  @GetMapping("/all")
  public List<InventoryDTO> getAllInventory () {
    List<InventoryDTO> inventoryDTOS = new ArrayList<>();
    for ( InventoryEntity inventoryEntity : inventoryService.getAllInventory()){
      inventoryDTOS.add(inventoryMapper.toDto(inventoryEntity));
    }
    return inventoryDTOS;
  }
  
  @GetMapping("/search")
  public List<InventoryDTO> searchInInventory (@RequestParam String query) {
    return inventoryService.searchInInventory(query).stream().map(
        inventoryMapper::toDto
    ).collect(Collectors.toList());
  }
  
  /**
   * Update inventory by id response entity.
   *
   * @param id                       the id
   * @param createUpdateInventoryDTO the create update inventory dto
   *
   * @return the response entity
   *
   * @throws InventoryException  the inventory exception
   * @throws ValidationException the validation exception
   */
  @PutMapping("/{id}")
  public ResponseEntity<InventoryDTO> updateInventoryById (@PathVariable String id,
                                                           @RequestBody CreateUpdateInventoryDTO createUpdateInventoryDTO) throws InventoryException, ValidationException {
    InventoryEntity inventoryEntity = inventoryMapper.toEntity(createUpdateInventoryDTO);
    inventoryEntity = inventoryService.updateInventory(id, inventoryEntity);
    return ResponseEntity.ok(
        inventoryMapper.toDto(inventoryEntity)
    );
  }
  
  /**
   * Remove inventory.
   *
   * @param id the id
   *
   * @throws InventoryException  the inventory exception
   * @throws ValidationException the validation exception
   */
  @DeleteMapping("/{id}")
  public void removeInventory (@PathVariable String id) throws InventoryException, ValidationException {
    inventoryService.deleteInventoryById(id);
  }
}