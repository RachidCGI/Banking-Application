package org.bankingapp.entity;

import org.bankingapp.exception.InsufficientBalanceException;

/**
 * Class representing a savings account.
 */
public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.05;
    private static final double MAX_WITHDRAWAL = 1000;

    /**
     * Constructor for creating a SavingsAccount object.
     * @param name The name of the account holder.
     * @param initialBalance The initial balance of the account.
     */
    public SavingsAccount(String name, double initialBalance) {
        super(name, initialBalance);
    }

    /**
     * Method to withdraw money from the savings account.
     * @param amount The amount to withdraw.
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0 || amount > getBalance() ) {
            throw new InsufficientBalanceException("Insufficient balance or invalid amount.");
        }
        if (amount > MAX_WITHDRAWAL){
            throw new InsufficientBalanceException("The amount exceeds the maximum withdrawal limit of " + MAX_WITHDRAWAL + " euros.");
        }
        setBalance(getBalance() - amount);
    }
    /**
     * Method to calculate interest for the savings account.
     * @return The interest calculated for this month.
     */
    public double calculateInterest() {
        double interest= getBalance() * INTEREST_RATE;
        setBalance(getBalance() + interest);
        return interest;
    }

}
