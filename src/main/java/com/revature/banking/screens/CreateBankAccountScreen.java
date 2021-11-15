package com.revature.banking.screens;

import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.Account;
import com.revature.banking.models.AppUser;
import com.revature.banking.services.AccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class CreateBankAccountScreen extends Screen{

    private final UserService userService;
    private final AccountService accountService;

    public CreateBankAccountScreen(BufferedReader reader, ScreenRouter router, UserService userService, AccountService accountService) {
        super("CreateBankAccountScreen", "/createaccount", reader, router);
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void render() throws Exception {
        logger.log("Loading Create Bank Account Screen");
        logger.log("Loading Session User");

        AppUser sessionUser = userService.getSessionUser();
        if (sessionUser == null) {
            System.out.println("You are not currently logged in! Navigating to Login Screen");
            router.navigate("/login");
            return;
        }

        while (userService.isSessionActive()) {
            System.out.println("Bank Account Creation Menu");

            String menu = "1) Create a new checking account\n" +
                    "2) Create a new savings account\n" +
                    "3) Return to " + sessionUser.getFirstName() + "'s Dashboard\n" +
                    "> ";

            System.out.print(menu);

            String userSelection = reader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("User selected create a new checking account");
                    String accountType = "Checking";
                    AppUser accountOwner = userService.getSessionUser();
                    System.out.println("How much would you like to initially deposit into your checking account?: ");
                    String accountBalanceString = reader.readLine();
                    Double accountBalance = Double.parseDouble(accountBalanceString);


                    System.out.println("User deposited: " + accountBalance);

                    Account newAccount = new Account(accountType, accountOwner, accountBalance);


                    try {
                        accountService.createNewCheckingAccount(newAccount);
                        router.navigate("/dashboard");
                    } catch (InvalidRequestException | ResourcePersistenceException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        //TODO log this unexpected exception to a file
                        e.printStackTrace();
                    }

                    break;
                case "2":
                    System.out.println("User selected create a new savings account");
                    accountType = "Savings";
                    accountOwner = userService.getSessionUser();
                    System.out.println("How much would you like to initially deposit into your savings account?: ");
                    accountBalanceString = reader.readLine();
                    accountBalance = Double.parseDouble(accountBalanceString);

                    newAccount = new Account(accountType, accountOwner, accountBalance);

                    try {
                        accountService.createNewSavingsAccount(newAccount);
                        router.navigate("/dashboard");
                    } catch (InvalidRequestException | ResourcePersistenceException e) {
                        System.out.println(e.getMessage());
                } catch (Exception e) {
                        //TODO log this unexpected exception to a file
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    System.out.println("Returning to Dashboard");
                    router.navigate("/dashboard");
                    break;
                default:
                    System.out.println("The user made an invalid selection");

            }
        }
    }
}
