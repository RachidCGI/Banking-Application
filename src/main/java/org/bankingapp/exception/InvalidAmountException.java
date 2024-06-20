package org.bankingapp.exception;

/**
 * Exception thrown when an invalid amount is encountered.
 */
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
}