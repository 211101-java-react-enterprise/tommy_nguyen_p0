package com.revature.banking.screens;

import com.revature.banking.models.AppUser;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;


public class AccountScreen extends Screen {

    private final UserService userService;

    public AccountScreen(BufferedReader reader, ScreenRouter router, UserService userService) {
        super("DashboardScreen", "/account", reader, router);
        this.userService = userService;
    }
    @Override
    public void render() throws Exception {

        AppUser sessionUser = userService.getSessionUser();

        if (sessionUser == null) {
            System.out.println("You are not currently logged in! Navigating to Login Screen");
            router.navigate("/login");
            return;
        }

        while (userService.isSessionActive()) {
            System.out.printf("\n%s's Dashboard\n", sessionUser.getFirstName());
            System.out.printf("Your total balance: $%.2f\n",(sessionUser.getBalance()));

            String menu = "1) Withdraw\n" +
                    "2) Deposit\n" +
                    "3) Logout\n" +
                    "> ";
            System.out.print(menu);

            String userSelection = reader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("user selected withdaw");
                    router.navigate("/withdraw"); //create WithdrawScreen
                    break;
                case "2":
                    System.out.println("user selected deposit");
                    router.navigate("/deposit"); //create DepositScreen
                case "3":
                    userService.logout();
                    break;
                default:
                    System.out.println("The user made an invalid selection");

            }
        }
    }
}
