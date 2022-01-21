package com.nuclei.assignment.enums;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.exception.CustomException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
   * The String Courses Map.
   */
  private static final Map<String, Courses> STRING_COURSES_MAP = new ConcurrentHashMap<>();
  
  /**
   * The Logger.
   */
  private static final Log LOGGER = LogFactory.getLog(Courses.class);
  
  static { 
    for (final Courses courses : Courses.values()) {
      STRING_COURSES_MAP.put(courses.toString(), courses);
    }
  }
  
  /**
   * Check whether course is present.
   *
   * @param course the course
   * @throws CustomException the custom exception
   */
  public static void checkWhetherCourseIsPresent(String course) throws CustomException {
    if (!STRING_COURSES_MAP.containsKey(course)) {
      LOGGER.error(String.format(ExceptionsConstantsUtils.INVALID_COURSE,
          course));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_COURSE,
          course));
    }
  }
}
