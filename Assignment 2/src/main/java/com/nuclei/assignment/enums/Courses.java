package com.nuclei.assignment.enums;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.exception.CustomException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
   * The String Courses Map.
   */
  private static final Map<String, Courses> stringCoursesMap = new ConcurrentHashMap<>();
  
  /**
   * The Logger.
   */
  private static final Log logger = LogFactory.getLog(Courses.class);
  
  static { 
    for (final Courses courses : Courses.values()) {
      stringCoursesMap.put(courses.toString(), courses);
    }
  }
  
  public static void checkWhetherCourseIsPresent(String course) throws CustomException {
    if (!stringCoursesMap.containsKey(course)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_COURSE,
        course));
      throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_COURSE,
        course));
    }
  }
}
