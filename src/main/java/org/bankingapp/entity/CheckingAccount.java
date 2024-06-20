package org.bankingapp.entity;

import org.bankingapp.exception.InsufficientBalanceException;

/**
 * Class representing a checking account.
 */
public class CheckingAccount extends Account {

    /**
     * Constructor for creating a CheckingAccount object.
     * @param name The name of the account holder.
     * @param initialBalance The initial balance of the account.
     */
    public CheckingAccount(String name, double initialBalance) {
        super(name, initialBalance);
    }

    /**
     * Method to withdraw money from the checking account.
     * @param amount The amount to withdraw.
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0 || amount > getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance or invalid amount.");
        }
        setBalance(getBalance() - amount);
    }
}
