package entity;

import enums.ItemType;
import lombok.Getter;
import lombok.Setter;

/**
 * Item Entity class.
 * **/
@Getter
@Setter
public class Item {
  private String name;
  
  private Double price;
  private Double salesTax;
  private Double finalPrice;
  
  private Double quantity;
  private ItemType type;
  
  /**
   * Item Constructor.
   * **/
  public Item(final String name, final Double price, final Double quantity,
              final ItemType type) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.type = type;
  }
  
}
