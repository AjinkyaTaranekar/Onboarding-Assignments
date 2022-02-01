package com.nuclei.example.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The enum Inventory type.
 */
public enum InventoryType {
  
  /**
   * Raw inventory type.
   */
  RAW,
  
  /**
   * Manufactured inventory type.
   */
  MANUFACTURED,
  
  /**
   * Imported inventory type.
   */
  IMPORTED;
  
  private static final Map<String, InventoryType> STRING_INVENTORY_TYPE_MAP =
      new ConcurrentHashMap<>();
  
  static {
    for (final InventoryType inventoryType : InventoryType.values()) {
      STRING_INVENTORY_TYPE_MAP.put(inventoryType.toString(), inventoryType);
    }
  }
  
  /**
   * Check whether inventory type is present boolean.
   *
   * @param inventoryType the inventory type
   *
   * @return the boolean
   */
  public static boolean checkWhetherInventoryTypeIsPresent (String inventoryType) {
    return STRING_INVENTORY_TYPE_MAP.containsKey(inventoryType);
  }
}
