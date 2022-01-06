package exception;

/**
 * Custom Exception.
 * **/
public class CustomException extends Exception {
  public CustomException(final String str) {
    // calling the constructor of parent Exception
    super(str);
  }
}
