package com.nuclei.assignment.entity;

import com.nuclei.assignment.enums.ItemType;
import lombok.Getter;
import lombok.Setter;

/**
 * Item Entity class.
 */
@Getter
@Setter
public class ItemEntity {
  
  /**
   * The Name.
   */
  public String name;
  
  /**
   * The Price.
   */
  private Double price;
  
  /**
   * The Sales tax.
   */
  private Double salesTax;
  
  /**
   * The Final price.
   */
  private Double finalPrice;
  
  /**
   * The Quantity.
   */
  private Double quantity;
  
  /**
   * The Type.
   */
  private ItemType type;
  
  /**
   * Item Constructor.
   *
   * @param name     the name
   * @param price    the price
   * @param quantity the quantity
   * @param type     the type
   */
  public ItemEntity(final String name, final Double price, final Double quantity,
              final ItemType type) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.type = type;
  }
  
}
