package com.nuclei.assignment.entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


/**
 * Node Entity class.
 */
@Getter
@Setter
public class NodeEntity implements Comparable<NodeEntity> {
  
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
  private Map<String, String> information;
  
  /**
   * The Parents.
   */
  private Set<Integer> parents;
  
  /**
   * The Children.
   */
  private Set<Integer> children;
  
  /**
   * Node Entity Constructor with id.
   *
   * @param id the id
   */
  public NodeEntity(final Integer id) {
    this.id = id;
  }
  
  /**
   * Node Entity Constructor with id.
   *
   * @param name the name
   * @param id   the id
   */
  public NodeEntity(final Integer id, final String name,
                    final Map<String, String> information) {
    this.id = id;
    this.name = name;
    this.information = information;
    this.parents = new HashSet<>();
    this.children = new HashSet<>();
  }
  
  @Override
  public int compareTo(final NodeEntity node) {
    return this.getId().compareTo(node.getId());
  }
  
  @Override
  public String toString() {
    return "NodeEntity{"
      + "name='" + name + '\''
      + ", id=" + id
      + ", information=" + information
      + ", parents=" + parents
      + ", children=" + children
      + '}';
  }
  
}
