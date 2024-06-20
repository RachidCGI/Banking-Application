package org.bankingapp.exception;

/**
 * Exception thrown when an invalid account type is encountered.
 */
public class InvalidAccountException extends RuntimeException{
    public InvalidAccountException(String message) {
        super(message);
    }
}
