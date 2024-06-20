package org.bankingapp.repository;

import org.bankingapp.entity.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Repository class for storing and retrieving accounts.
 */
public class AccountRepository {
    private final Map<Integer, Account> accounts = new HashMap<>();

    /**
     * Method to get all accounts stored in the repository.
     * @return A map of account IDs to Account objects.
     */
    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    /**
     * Method to save an account in the repository.
     * @param account The account to be saved.
     * @return The saved account.
     */
    public Account save(Account account) {
        accounts.put(account.getAccountId(), account);
        return account;
    }

    /**
     * Method to find an account by its ID.
     * @param accountId The ID of the account to find.
     * @return An Optional containing the found Account object, or empty if not found.
     */
    public Optional<Account> findById(int accountId) {

        return Optional.ofNullable(accounts.get(accountId));
    }


}
