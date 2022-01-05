package exception;

/**
 * Custom Exception
 * **/
public class CustomException extends Exception{
    public CustomException (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
