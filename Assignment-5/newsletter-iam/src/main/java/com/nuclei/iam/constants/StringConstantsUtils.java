package com.nuclei.iam.constants;

/**
 * The type String constants utils.
 */
public final class StringConstantsUtils {
  /**
   * The constant ID.
   */
  public static final String ID = "Id";
  
  /**
   * The constant NAME.
   */
  public static final String NAME = "Name";
  
  /**
   * The constant JWT.
   */
  public static final String JWT = "jwt";
  
  /**
   * The constant EMAIL.
   */
  public static final String EMAIL = "Email";
  
  /**
   * The constant PASSWORD.
   */
  public static final String PASSWORD = "Password";
  
  /**
   * The constant PASSWORD_POLICY.
   */
  public static final String PASSWORD_POLICY =
      "The password policy is:\n"
          + "- At least 8 chars\n"
          + "- Contains at least one digit\n"
          + "- Contains at least one lower alpha char and one upper alpha "
          + "char\n"
          + "- Contains at least one char within a set of special chars (@#%$^ "
          + "etc.)\n"
          + "- Does not contain space, tab, etc.";
}
