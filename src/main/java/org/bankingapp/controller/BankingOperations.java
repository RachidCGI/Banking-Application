package org.bankingapp.controller;

import org.bankingapp.entity.Account;
import org.bankingapp.exception.AccountNotFoundException;
import org.bankingapp.exception.InsufficientBalanceException;
import org.bankingapp.helper.BankHelper;
import org.bankingapp.service.AccountService;

/**
 * Controller class for handling banking operations.
 */
public class BankingOperations {
    private final AccountService accountService;
    private final BankHelper helper;

    /**
     * Constructor to initialize BankingOperations with dependencies.
     * @param accountService The service handling account operations.
     * @param helper The helper for user input.
     */
    public BankingOperations(AccountService accountService, BankHelper helper) {
        this.accountService = accountService;
        this.helper = helper;
    }

    /**
     * Method to create a new account.
     */
    public void createAccount() {
        System.out.println("\n--- Create Account ---");
        System.out.print("Enter your name: ");
        String name = helper.getNextStringInput();
        System.out.print("Enter the initial balance: ");
        double initialBalance = helper.getNextDoubleInput();
        System.out.print("Choose the account type (1 for checking, 2 for savings): ");
        int type = helper.getNextAccountTypeInput();
        String accountType = type == 1 ? "checking" : "savings";
        Account account = accountService.createAccount(name, initialBalance, accountType);
        System.out.println(accountType + " account created successfully. Account identifier: " + account.getAccountId());
    }

    /**
     * Method to deposit money into an account.
     */

    public void deposit() {
        System.out.println("\n--- Deposit Money ---");
        System.out.print("Enter your account identifier: ");
        int accountId = helper.getNextIntInput();
        try {
            Account account = accountService.findAccountById(accountId);
            System.out.print("Enter the amount to deposit: ");
            double amount = helper.getNextDoubleInput();
            accountService.deposit(account.getAccountId(), amount);
            System.out.println(amount + " euros have been deposited into your account.");

        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to withdraw money from an account.
     */
    public void withdraw() {
        System.out.println("\n--- Withdraw Money ---");
        System.out.print("Enter your account identifier: ");
        int accountId = helper.getNextIntInput();
        try {
            Account account = accountService.findAccountById(accountId);
            System.out.print("Enter the amount to withdraw: ");
            double amount = helper.getNextDoubleInput();
            accountService.withdraw(account.getAccountId(), amount);
            System.out.println(amount + " euros have been withdrawn from your account.");

        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to display the balance of an account.
     */
    public void displayBalance() {
        System.out.println("\n--- Display Balance ---");
        System.out.print("Enter your account identifier: ");
        int accountId = helper.getNextIntInput();
        try {
            Account account = accountService.findAccountById(accountId);
            double balance = accountService.getBalance(account.getAccountId());
            System.out.println("Your balance is " + balance + " euros.");

        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Method to calculate interest for a savings account.
     */
    public void calculateInterest() {
        System.out.println("\n--- Calculate Interest ---");
        System.out.print("Enter your account identifier: ");
        int accountId = helper.getNextIntInput();
        try {
            Account account = accountService.findAccountById(accountId);
            double interest = accountService.calculateInterest(account.getAccountId());
            System.out.println("Interest for this month is " + interest + " euros.");

        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}