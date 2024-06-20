package org.bankingapp.service;

import org.bankingapp.entity.Account;
import org.bankingapp.entity.CheckingAccount;
import org.bankingapp.entity.SavingsAccount;
import org.bankingapp.exception.AccountNotFoundException;
import org.bankingapp.exception.InsufficientBalanceException;
import org.bankingapp.exception.InvalidAccountException;
import org.bankingapp.repository.AccountRepository;

import java.util.Map;
import java.util.Optional;

/**
 * Service class for performing operations on accounts.
 */
public class AccountService {
    private final AccountRepository accountRepository;

    /**
     * Constructor to initialize AccountService with an AccountRepository.
     * @param accountRepository The repository for account data.
     */
    public AccountService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    /**
     * Method to create an account.
     * @param name The name of the account holder.
     * @param initialBalance The initial balance of the account.
     * @param type The type of account ('checking' or 'savings').
     * @return The created Account object.
     */
    public Account createAccount(String name, double initialBalance, String type) {
        Account account;
        if ("checking".equalsIgnoreCase(type)) {
            account = new CheckingAccount(name, initialBalance);
        } else if ("savings".equalsIgnoreCase(type)) {
            account = new SavingsAccount(name, initialBalance);
        } else {
            throw new InvalidAccountException("Invalid account type.");
        }
        return accountRepository.save(account);
    }

    /**
     * Method to deposit money into an account.
     * @param accountId The ID of the account.
     * @param amount The amount to deposit.
     */
    public void deposit(int accountId, double amount) {
        Account account = findAccountById(accountId);
        account.deposit(amount);
        accountRepository.save(account);
    }

    /**
     * Method to withdraw money from an account.
     * @param accountId The ID of the account.
     * @param amount The amount to withdraw.
     */
    public void withdraw(int accountId, double amount) throws InsufficientBalanceException {
            Account account = findAccountById(accountId);
            account.withdraw(amount);
            accountRepository.save(account);
    }

    /**
     * Method to get the balance of an account.
     * @param accountId The ID of the account.
     * @return The balance of the account.
     */
    public double getBalance(int accountId) {

        return findAccountById(accountId).getBalance();
    }

    /**
     * Method to calculate interest for a savings account.
     * @param accountId The ID of the savings account.
     * @return The interest calculated for this month.
     */
    public double calculateInterest(int accountId) {
        Account account = findAccountById(accountId);
        if (account instanceof SavingsAccount) {
           double interest= ((SavingsAccount) account).calculateInterest();
            accountRepository.save(account);
            return interest;
        } else {
            throw new IllegalArgumentException("Interest calculation is only applicable for savings accounts.");
        }
    }

    /**
     * Method to find an account by its ID.
     * @param accountId The ID of the account to find.
     * @return The found Account object.
     * @throws AccountNotFoundException If the account with the given ID is not found.
     */
    public Account findAccountById(int accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException("Account with ID " + accountId + " not found.");
        }
        return optionalAccount.get();
    }

    /**
     * Method to get all accounts stored in the repository.
     * @return A map of account IDs to Account objects.
     */
    public Map<Integer, Account> getAllAccounts() {

        return accountRepository.getAccounts();
    }
}
