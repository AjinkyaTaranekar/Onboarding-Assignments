package com.nuclei.example.dto;

import com.nuclei.example.enums.InventoryType;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Create update inventory dto.
 */
@Getter
@Setter
public class CreateUpdateInventoryDTO {
  
  private String name;
  
  private Double price;
  
  private Double quantity;
  
  private InventoryType type;
}
