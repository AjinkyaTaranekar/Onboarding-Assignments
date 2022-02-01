package com.nuclei.example.entity;

import com.nuclei.example.enums.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * The type Inventory entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "inventory")
public class InventoryEntity {
  
  @Id
  private String id;
  
  private String name;
  
  private Double price;
  
  private Double salesTax;
  
  private Double finalPrice;
  
  private Double quantity;
  
  private InventoryType type;
  
  /**
   * Instantiates a new Inventory entity.
   *
   * @param name       the name
   * @param price      the price
   * @param salesTax   the sales tax
   * @param finalPrice the final price
   * @param quantity   the quantity
   * @param type       the type
   */
  public InventoryEntity (String name, Double price, Double salesTax, Double finalPrice, Double quantity, InventoryType type) {
    this.name = name;
    this.price = price;
    this.salesTax = salesTax;
    this.finalPrice = finalPrice;
    this.quantity = quantity;
    this.type = type;
  }
  
}