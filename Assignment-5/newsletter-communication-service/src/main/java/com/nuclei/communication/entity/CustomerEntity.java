package com.nuclei.communication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;


/**
 * The type Customer entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity implements Serializable {
  
  /**
   * The Id.
   */
  private Integer id;
  
  /**
   * The Name.
   */
  private String name;
  
  /**
   * The Email id.
   */
  private String emailId;
  
}