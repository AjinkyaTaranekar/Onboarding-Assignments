package com.nuclei.example.entity;

import com.nuclei.example.enums.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Inventory entity.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory")
public class InventoryEntity implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  
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