# Banking Application

This banking application allows users to manage bank accounts, perform operations such as deposits, withdrawals, calculate interest for savings accounts, and display current balances.

## Technologies Used

- **Java 17**
- **Maven**

## Prerequisites

- **Java Development Kit (JDK) version 17** or higher
- **Maven**

## Dependencies

- **JUnit 5**: Testing framework for unit and integration tests.
- **Mockito**: Mocking framework for creating mock objects during testing.

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/RachidCGI/Banking-Application.git

2. **Build the project with Maven:** 

   ```bash
   mvn clean install

## Run the application

After building the application with Maven, execute it with the following command from the project root: 

   ```bash
   java -jar target/BankingApplication-1.0-SNAPSHOT.jar
   ```


## Navigating the Application

The application displays a main menu with the following options:

1. **Create an account**
2. **Deposit money**
3. **Withdraw money**
4. **Display balance**
5. **Calculate interest for a savings account**
6. **Quit**

Select an option by entering the corresponding number and follow the on-screen instructions to perform the desired operations.

## Testing

The project includes unit tests for key functionalities using JUnit and Mockito to test various parts of the application, including services and helpers for handling user inputs.

To run unit tests, use the following Maven command:

```bash
mvn test