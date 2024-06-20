package org.bankingapp;

import org.bankingapp.helper.BankHelper;
import org.junit.Test;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.Assert.*;

public class BankHelperTest {

    // Test reading a double input
    @Test
    public void testGetNextDoubleInput() {
        Scanner scanner = new Scanner("12.34\n").useLocale(Locale.US);
        BankHelper helper = new BankHelper(scanner);
        assertEquals(12.34, helper.getNextDoubleInput(), 0);
    }

    // Test reading a string input
    @Test
    public void testGetNextStringInput() {
        Scanner scanner = new Scanner("Patrick\n");
        BankHelper helper = new BankHelper(scanner);
        assertEquals("Patrick", helper.getNextStringInput());
    }

    // Test reading an account type input
    @Test
    public void testGetNextAccountTypeInput() {
        Scanner scanner = new Scanner("2\n");
        BankHelper helper = new BankHelper(scanner);
        assertEquals(2, helper.getNextAccountTypeInput());
    }

    // Test reading an invalid integer input, expecting an exceptions
    @Test(expected = InputMismatchException.class)
    public void testGetNextIntInput() {
        Scanner scanner = new Scanner("abc\n");
        BankHelper helper = new BankHelper(scanner);
        helper.getNextIntInput();
    }
    // Test reading a double input with multiple entries
    @Test
    public void testGetNextDoubleInputMultipleEntries() {
        Scanner scanner = new Scanner("abc\n12.34\n").useLocale(Locale.US);
        BankHelper helper = new BankHelper(scanner);
        helper.getNextStringInput(); // Read the invalid input first
        assertEquals(12.34, helper.getNextDoubleInput(), 0);
    }



    // Test reading an integer input with zero
    @Test
    public void testGetNextIntInputZero() {
        Scanner scanner = new Scanner("0\n");
        BankHelper helper = new BankHelper(scanner);
        assertEquals(0, helper.getNextIntInput());
    }

    // Test reading an integer input with negative number
    @Test
    public void testGetNextIntInputNegative() {
        Scanner scanner = new Scanner("-100\n");
        BankHelper helper = new BankHelper(scanner);
        assertEquals(-100, helper.getNextIntInput());
    }

    // Test reading an account type input with invalid inputs
    @Test
    public void testGetNextAccountTypeInputInvalidInputs() {
        Scanner scanner = new Scanner("3\n4\n1\n");
        BankHelper helper = new BankHelper(scanner);
        assertEquals(1, helper.getNextAccountTypeInput());
    }

    // Test reading an account type input with invalid initial input
    @Test
    public void testGetNextAccountTypeInputInvalidInitialInput() {
        Scanner scanner = new Scanner("abc\n1\n");
        BankHelper helper = new BankHelper(scanner);
        helper.getNextStringInput(); // Read the invalid input first
        assertEquals(1, helper.getNextAccountTypeInput());
    }
}