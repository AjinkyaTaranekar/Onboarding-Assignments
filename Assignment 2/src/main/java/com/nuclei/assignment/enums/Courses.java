package com.nuclei.assignment.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User Types.
 */
public enum Courses {
  /**
   * A courses.
   */
  A,
  /**
   * B courses.
   */
  B,
  /**
   * C courses.
   */
  C,
  /**
   * D courses.
   */
  D,
  /**
   * E courses.
   */
  E,
  /**
   * F courses.
   */
  F;
  
  /**
   * The constant STRING_COURSES_MAP.
   */
  public static final Map<String, Courses> STRING_COURSES_MAP;
  
  static {
    STRING_COURSES_MAP = getCoursesConstantsMap();
  }
  
  private static Map<String, Courses> getCoursesConstantsMap() {
    final Map<String, Courses> coursesConstantsMap = new ConcurrentHashMap<>();
    for (final Courses courses : Courses.values()) {
      coursesConstantsMap.put(courses.toString(), courses);
    }
    return coursesConstantsMap;
  }
}
