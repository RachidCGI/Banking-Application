package org.bankingapp;

import org.bankingapp.controllor.BankingOperations;
import org.bankingapp.helper.BankHelper;
import org.bankingapp.repository.AccountRepository;
import org.bankingapp.service.AccountService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**

 Main class to run the banking application.
 */
public class App {
    private final BankingOperations bankingOperations;
    private final Scanner scanner;
    private final BankHelper helper;

    /**

     Constructor to initialize the banking application.
     */
    public App() {
        AccountService accountService = new AccountService(new AccountRepository());
        this.scanner = new Scanner(System.in).useLocale(Locale.US);
        this.helper = new BankHelper(this.scanner);
        this.bankingOperations = new BankingOperations(accountService, helper);
    }
    /**

     Main method to start the banking application.
     */
    public static void main(String[] args) {
        App bankingApp = new App();
        bankingApp.run();
    }
    /**

     Method to run the main loop of the banking application.
     */
    public void run() {
        printMainMenu();
        while (true) {
            System.out.print("Enter your choice: ");

            try {
                int choice = helper.getNextIntInput();
                switch (choice) {
                    case 1:
                        bankingOperations.createAccount();
                        break;
                    case 2:
                        bankingOperations.deposit();
                        break;
                    case 3:
                        bankingOperations.withdraw();
                        break;
                    case 4:
                        bankingOperations.displayBalance();
                        break;
                    case 5:
                        bankingOperations.calculateInterest();
                        break;
                    case 6:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("Error: Something went wrong.");
            }
        }
    }
    /**

     Method to print the main menu of the banking application.
     */
    private void printMainMenu() {
        System.out.println("\n==============================");
        System.out.println(" Banking Application ");
        System.out.println("==============================");
        System.out.println("1. Create an account");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Display balance");
        System.out.println("5. Calculate interest for a savings account");
        System.out.println("6. Quit");
        System.out.println("==============================\n");
    }
}