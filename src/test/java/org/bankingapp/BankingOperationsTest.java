package org.bankingapp;
import org.bankingapp.controllor.BankingOperations;
import org.bankingapp.entity.Account;
import org.bankingapp.helper.BankHelper;
import org.bankingapp.repository.AccountRepository;
import org.bankingapp.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import static org.junit.Assert.*;

public class BankingOperationsTest {
    private BankingOperations bankingOperations;
    private AccountService accountService;
    private BankHelper helper;

    // Set up the BankingOperations, AccountService, and mock BankHelper before each test
    @Before
    public void setUp() {
        accountService = new AccountService(new AccountRepository());
        helper = Mockito.mock(BankHelper.class);
        bankingOperations = new BankingOperations(accountService, helper);
    }

    // Test the creation of an account through BankingOperations
    @Test
    public void testCreateAccount() {
        Mockito.when(helper.getNextStringInput()).thenReturn("Patrick Kan");
        Mockito.when(helper.getNextDoubleInput()).thenReturn(1000.0);
        Mockito.when(helper.getNextAccountTypeInput()).thenReturn(1);

        bankingOperations.createAccount();
        assertEquals(1, accountService.getAllAccounts().size());
    }

    // Test depositing money through BankingOperations
    @Test
    public void testDeposit() {
        Account account = accountService.createAccount("Patrick Kan", 2000, "savings");
        Mockito.when(helper.getNextIntInput()).thenReturn(account.getAccountId());
        Mockito.when(helper.getNextDoubleInput()).thenReturn(500.0);

        bankingOperations.deposit();
        assertEquals(2500, account.getBalance(), 0);
    }

    // Test withdrawing money through BankingOperations
    @Test
    public void testWithdraw() {
        Account account = accountService.createAccount("Patrick Kan", 1000, "checking");
        Mockito.when(helper.getNextIntInput()).thenReturn(account.getAccountId());
        Mockito.when(helper.getNextDoubleInput()).thenReturn(500.0);

        bankingOperations.withdraw();
        assertEquals(500, account.getBalance(), 0);
    }

    // Test displaying balance through BankingOperations
    @Test
    public void testDisplayBalance() {
        Account account = accountService.createAccount("Patrick Kan", 1000, "checking");
        Mockito.when(helper.getNextIntInput()).thenReturn(account.getAccountId());

        bankingOperations.displayBalance();
        assertEquals(1000, accountService.getBalance(account.getAccountId()), 0);
    }

    // Test calculating interest through BankingOperations
    @Test
    public void testCalculateInterest() {
        Account account = accountService.createAccount("Patrick Kan", 2000, "savings");
        Mockito.when(helper.getNextIntInput()).thenReturn(account.getAccountId());

        bankingOperations.calculateInterest();
        assertEquals(2100, account.getBalance(), 0);
    }
}