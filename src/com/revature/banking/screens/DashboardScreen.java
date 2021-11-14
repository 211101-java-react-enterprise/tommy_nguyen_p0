package com.revature.banking.screens;

import com.revature.banking.models.AppUser;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;


public class DashboardScreen extends Screen{

    private final UserService userService;

    public DashboardScreen(BufferedReader reader, ScreenRouter router, UserService userService) {
        super("DashboardScreen", "/dashboard", reader, router);
        this.userService = userService;
    }
    @Override
    public void render() throws Exception {

        //TODO implement user options and make this screen loop until user logs out.
        AppUser sessionUser = userService.getSessionUser();
        if (sessionUser == null) {
            System.out.println("You are not currently logged in! Navigating to Login Screen");
            router.navigate("/login");
            return;
        }

        while (userService.isSessionActive()) {
            System.out.printf("\n%s's Dashboard\n", sessionUser.getFirstName());

            String menu = "1) View/edit my profile information\n" +
                    "2) View/create accounts\n" +
                    "3) logout\n" +
                    "> ";
            System.out.print(menu);

            String userSelection = reader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("View/edit profile selected.");
                    break;
                case "2":
                    System.out.println("View/create accounts selected");
                    break;
                case "3":
                    userService.logout();
                    break;
                default:
                    System.out.println("The user made an invalid selection");

            }
        }
    }
}
