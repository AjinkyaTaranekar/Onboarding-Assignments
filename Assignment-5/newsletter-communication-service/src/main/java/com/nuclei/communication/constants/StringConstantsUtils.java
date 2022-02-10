package com.nuclei.communication.constants;

/**
 * The type String constants utils.
 */
public final class StringConstantsUtils {
  
  /**
   * The constant NAME.
   */
  public static final String NAME = "Name";
  
  /**
   * The constant EMAIL.
   */
  public static final String EMAIL = "Email";
  
  /**
   * The constant PASSWORD.
   */
  public static final String OTP_BODY =
      "Hello %s, \n\n"
          + "Verify you email for Newsletter Service %s\n"
          + "with the OTP as %s"
          + "\nOTP expires in %s minutes"
          + "\n\nThanks & Regards"
          + "\nNewsExpress";
  
  
  public static final String OTP_SUBJECT =
      "%s | E-mail Verification for Newsletter.";
  
}
