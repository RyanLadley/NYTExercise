package Utilities;

/**
 * Custom Exception to be thrown by the program whenever a "Real" Exception is caught, or when a program breaking instance is found. 
 *
 */
@SuppressWarnings("serial")
public class CompareException extends Exception{

	public CompareException(String message) {
        super(message);
    }
}
