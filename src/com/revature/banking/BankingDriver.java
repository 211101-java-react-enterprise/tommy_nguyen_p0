package com.revature.banking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class BankingDriver {
    /*
     * User Stories:
     * as a user I can
     *      - register a new user account with the system (must be secured with a password)
     *      - login with my existing credentials
     *      - deposit funds into an account (use doubles, not ints)
     *      - withdraw funds from an account (no overdrafting!)
     *      - view the balance of my account(s) (all balance displays must be in proper currency format)
     *
     * Suggested Bonus User Stories:
     * as a user I can
     *      - view the transaction history for an account
     *      - create multiple accounts per user (checking, savings, etc.)
     *      - share a joint account with another user
     *      - transfer money between accounts
     */

    static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        boolean isRunning = true;

        while (isRunning) {

            System.out.print("Welcome to Bank of Revature!\n" +
                    "1) Login\n" +
                    "2) Register\n" +
                    "3) Exit\n" +
                    "> ");

            String userSelection = null;

            try {
                userSelection = consoleReader.readLine();
                System.out.println("User selected: " + userSelection); //BREAD CRUMB STATEMENT

                switch (userSelection) {
                    case "1":
                        login();
                        break;
                    case "2":
                        register();
                        break;
                    case "3":
                        System.out.println("The user selected Exit");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("The user made an invalid selection");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            consoleReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void login() {
        System.out.println("The user selected Login");
    }

    public static void register() throws IOException {
        System.out.println("The user selected Register");
        System.out.println("Please provide us with some basic information");

        System.out.print("First name: ");
        String firstName = consoleReader.readLine();
        System.out.print("Last name: ");
        String lastName = consoleReader.readLine();
        System.out.print("Email: ");
        String email = consoleReader.readLine();
        System.out.print("Username: ");
        String username = consoleReader.readLine();
        System.out.print("Password: ");
        String password = consoleReader.readLine();

        System.out.printf("Provided user info: { \"firstName\": %s, \"lastName\": %s, \"email\": %s, \"username\": %s, \"password\": %s }\n",
                firstName, lastName, email, username, password); //BREAD CRUMB STATMENT

    }
}
