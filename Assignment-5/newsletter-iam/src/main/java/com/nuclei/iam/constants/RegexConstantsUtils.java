package com.nuclei.iam.constants;

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
  
  /**
   * The constant VALID_PASSWORD_REGEX.
   */
  public static final Pattern VALID_PASSWORD_REGEX =
      Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
  
  /**
   * The constant VALID_JWT_REGEX.
   */
  public static final Pattern VALID_JWT_REGEX =
      Pattern.compile("^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$");
  
}
