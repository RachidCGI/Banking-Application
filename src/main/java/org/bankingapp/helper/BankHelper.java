package org.bankingapp.helper;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Helper class for handling user input in the banking application.
 */
public class BankHelper {
    private final Scanner scanner;

    /**
     * Constructor to initialize BankHelper with a Scanner object.
     * @param scanner The scanner object for user input.
     */
    public BankHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Method to get the next double input from the user.
     */
    public double getNextDoubleInput() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }

    /**
     * Method to get the next string input from the user.
     * Ensures the input contains only letters.
     * @return The valid string input.
     */
    public String getNextStringInput() {
        while (true) {
            String input = scanner.next();
            if (input.matches("[a-zA-Z]+")) {
                return input;
            } else {
                System.out.println("Error: Invalid name. Please enter a valid name containing only letters.");
            }
        }
    }

    /**
     * Method to get the next account type input from the user.
     * Validates that the input is either 1 (checking) or 2 (savings).
     * @return The valid account type input.
     */
    public int getNextAccountTypeInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input == 1 || input == 2) {
                    return input;
                } else {
                    System.out.println("Error: Invalid input. Please enter 1 for checking or 2 for savings.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number (1 or 2).");
                scanner.next();
            }
        }
    }

    /**
     * Method to get the next integer input from the user.
     * @return The valid integer input.
     */
    public int getNextIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                throw  e;
            }
        }
    }
}
