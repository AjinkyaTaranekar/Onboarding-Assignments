package com.nuclei.assignment.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


/**
 * User Entity class.
 */
@Getter
@Setter
public class UserEntity implements Comparable<UserEntity> {
  
  /**
   * The Name.
   */
  private String name;
  
  /**
   * The Id.
   */
  private Integer id;
  
  /**
   * The Information.
   */
  private Map<String, Object> information;
  
  /**
   * The Parents.
   */
  private Set<Integer> parents;
  
  /**
   * The Children.
   */
  private Set<Integer> children;
  
  /**
   * User Entity Constructor with id.
   *
   * @param id the id
   */
  public UserEntity(final Integer id) {
    this.id = id;
  }
  
  /**
   * User Entity Constructor with id.
   *
   * @param name the name
   * @param id   the id
   */
  public UserEntity(final String name, final Integer id) {
    this.name = name;
    this.id = id;
    this.parents = new HashSet<>();
    this.children = new HashSet<>();
    this.information = new HashMap<>();
  }
  
  @Override
  public int compareTo(final UserEntity user) {
    return this.getId().compareTo(user.getId());
  }
  
  @Override
  public String toString() {
    return "UserEntity{"
      + "name='" + name + '\''
      + ", id=" + id
      + ", information=" + information
      + ", parents=" + parents
      + ", children=" + children
      + '}';
  }
  
}
