package com.nuclei.communication.constants;

import java.util.regex.Pattern;

/**
 * The type Regex constants utils.
 */
public final class RegexConstantsUtils {
  
  /**
   * The constant VALID_EMAIL_ID_REGEX.
   */
  public static final Pattern VALID_EMAIL_ID_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
  
}
