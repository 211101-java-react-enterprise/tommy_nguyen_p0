package com.revature.banking.screens;

import com.revature.banking.models.Account;
import com.revature.banking.models.AppUser;
import com.revature.banking.services.AccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class ViewBankAccountScreen extends Screen{

    private final UserService userService;
    private final AccountService accountService;

    public ViewBankAccountScreen(BufferedReader reader, ScreenRouter router, UserService userService, AccountService accountService) {
        super("ViewBankAccountScreen", "/viewaccount", reader, router);
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void render() throws Exception {

        logger.log("Loading View Bank Account Screen");
        logger.log("Loading Session User");

        AppUser sessionUser = userService.getSessionUser();
        if (sessionUser == null) {
            System.out.println("You are not currently logged in! Navigating to Login Screen");
            router.navigate("/login");
            return;
        }

        while (userService.isSessionActive()) {
            System.out.println("View Bank Account Menu");

            String menu = "1) View checking account\n" +
                        "2) View savings account\n " +
                        "3) Return to " + sessionUser.getFirstName() + "'s Dashboard\n" +
                        "> ";

            System.out.println(menu);

            String userSelection = reader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("User selected view checking account");
                    if (acco)
                    break;
                case "2":
                    System.out.println("User selected view savings account");
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
