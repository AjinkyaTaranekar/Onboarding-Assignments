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
   * The metadata.
   */
  private Map<String, String> metadata;
  
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
   * @param id       the id
   * @param name     the name
   * @param metadata the metadata
   */
  public NodeEntity(final Integer id, final String name,
                    final Map<String, String> metadata) {
    this.id = id;
    this.name = name;
    this.metadata = metadata;
    this.parents = new HashSet<>();
    this.children = new HashSet<>();
  }
  
  @Override
  public int compareTo(final NodeEntity node) {
    return this.getId().compareTo(node.getId());
  }
  
}
