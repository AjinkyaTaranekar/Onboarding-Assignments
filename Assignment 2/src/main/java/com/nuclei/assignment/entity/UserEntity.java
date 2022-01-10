package com.nuclei.assignment.entity;

import com.nuclei.assignment.enums.Courses;
import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * User Entity class.
 */
@Getter
@Setter
@AllArgsConstructor
public class UserEntity implements Serializable {
  /**
   * The Name.
   */
  private String name;
  /**
   * The Age.
   */
  private Integer age;
  /**
   * The Address.
   */
  private String address;
  /**
   * The Roll number.
   */
  private Integer rollNumber;
  /**
   * The Courses.
   */
  private Set<Courses> courses;
  
  /**
   * User Constructor with only roll number.
   *
   * @param rollNumber the roll number
   */
  public UserEntity(final int rollNumber) {
    this.rollNumber = rollNumber;
  }
  
  @Override
  public String toString() {
    return "UserEntity{"
      + "name='" + name + '\''
      + ", age=" + age
      + ", address='" + address + '\''
      + ", rollNumber=" + rollNumber
      + ", courses=" + courses
      + '}';
  }
}
