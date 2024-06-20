package org.bankingapp;
import org.bankingapp.entity.Account;
import org.bankingapp.exception.AccountNotFoundException;
import org.bankingapp.exception.InsufficientBalanceException;
import org.bankingapp.repository.AccountRepository;
import org.bankingapp.service.AccountService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountServiceTest {
    private AccountService accountService;

    // Set up the AccountService before each test
    @Before
    public void setUp() {
        accountService = new AccountService(new AccountRepository());
    }

    // Test the creation of a new account
    @Test
    public void testCreateAccount() {
        Account account = accountService.createAccount("Patrick Kan", 1000, "checking");
        assertNotNull(account);
        assertEquals("Patrick Kan", account.getOwnerName());
        assertEquals(1000, account.getBalance(), 0);
    }

    // Test depositing money into an account
    @Test
    public void testDeposit() {
        Account account = accountService.createAccount("Patrick Kan", 2000, "savings");
        accountService.deposit(account.getAccountId(), 500);
        assertEquals(2500, account.getBalance(), 0);
    }

    // Test depositing money into a non-existent account, expecting an exception
    @Test(expected = AccountNotFoundException.class)
    public void testDepositToNonExistentAccount() {

        accountService.deposit(999, 500);
    }

    // Test withdrawing money from an account
    @Test
    public void testWithdraw() {
        Account account = accountService.createAccount("Patrick Kan", 1000, "checking");
        accountService.withdraw(account.getAccountId(), 500);
        assertEquals(500, account.getBalance(), 0);
    }

    // Test withdrawing more money than the account balance, expecting an exception
    @Test(expected = InsufficientBalanceException.class)
    public void testWithdrawInsufficientBalance() {
        Account account = accountService.createAccount("Patrick Kan", 1000, "checking");
        accountService.withdraw(account.getAccountId(), 1500);
    }

    // Test retrieving the balance of an account
    @Test
    public void testGetBalance() {
        Account account = accountService.createAccount("Patrick Kan", 1000, "checking");
        assertEquals(1000, accountService.getBalance(account.getAccountId()), 0);
    }

    // Test calculating interest for a savings account
    @Test
    public void testCalculateInterest() {
        Account account = accountService.createAccount("Patrick Kan", 2000, "savings");
        double interest = accountService.calculateInterest(account.getAccountId());
        assertEquals(100, interest, 0);
    }
}
