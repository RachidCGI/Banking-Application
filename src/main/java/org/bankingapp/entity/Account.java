package org.bankingapp.entity;

import org.bankingapp.exception.InsufficientBalanceException;
import org.bankingapp.exception.InvalidAmountException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Abstract class representing a bank account.
 */
public abstract class Account {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    private final int accountId;
    private final String name;
    private double balance;

    /**
     * Constructor for creating an Account object.
     * @param name The name of the account holder.
     * @param initialBalance The initial balance of the account.
     */
    public Account(String name, double initialBalance) {
        this.accountId = idGenerator.getAndIncrement();
        this.name = name;
        this.balance = initialBalance;
    }

    /**
     * Getter for account ID.
     * @return The account ID.
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Getter for account balance.
     * @return The current balance of the account.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Method to deposit money into the account.
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }

    /**
     * Abstract method to withdraw money from the account.
     * @param amount The amount to withdraw.
     */
    public abstract void withdraw(double amount) throws InsufficientBalanceException;

    /**
     * Protected method to set the balance of the account.
     * @param balance The new balance to set.
     */
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Getter for account holder's name.
     * @return The account holder's name.
     */
    public String getOwnerName() {
        return this.name;
    }


}
