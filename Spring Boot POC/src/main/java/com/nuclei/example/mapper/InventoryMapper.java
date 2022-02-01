package com.nuclei.example.mapper;

import com.nuclei.example.dto.CreateUpdateInventoryDTO;
import com.nuclei.example.dto.InventoryDTO;
import com.nuclei.example.entity.InventoryEntity;
import org.mapstruct.Mapper;

/**
 * The interface Inventory mapper.
 */
@Mapper(componentModel = "spring")
public interface InventoryMapper {
  
  /**
   * To entity inventory entity.
   *
   * @param inventoryDto the inventory dto
   *
   * @return the inventory entity
   */
  InventoryEntity toEntity (InventoryDTO inventoryDto);
  
  /**
   * To entity inventory entity.
   *
   * @param createUpdateInventoryDTO the create update inventory dto
   *
   * @return the inventory entity
   */
  InventoryEntity toEntity (CreateUpdateInventoryDTO createUpdateInventoryDTO);
  
  /**
   * To dto inventory dto.
   *
   * @param inventoryEntity the inventory entity
   *
   * @return the inventory dto
   */
  InventoryDTO toDto (InventoryEntity inventoryEntity);
}
