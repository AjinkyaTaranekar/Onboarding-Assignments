package com.nuclei.example.dto;

import com.nuclei.example.enums.InventoryType;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Inventory dto.
 */
@Getter
@Setter
public class InventoryDTO {
  private Integer id;
  
  private String name;
  
  private Double price;
  
  private Double salesTax;
  
  private Double finalPrice;
  
  private Double quantity;
  
  private InventoryType type;
}
