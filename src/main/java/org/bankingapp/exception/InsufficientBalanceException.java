package org.bankingapp.exception;

/**
 * Exception thrown when there is insufficient balance in an account.
 */
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
