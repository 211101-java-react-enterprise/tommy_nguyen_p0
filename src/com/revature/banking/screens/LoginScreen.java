package com.revature.banking.screens;

import com.revature.banking.models.AppUser;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private final UserService userService;

    public LoginScreen(BufferedReader reader, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/login", reader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("Please enter your account username and password");
        System.out.print("Username: ");
        String username = reader.readLine();
        System.out.print("\nPassword: ");
        String password = reader.readLine();

        try {
            AppUser user = userService.authenticateUser(username, password);
        } catch (RuntimeException e) {
            System.out.println("Could not authenticate using provided credentials. Navigating back to Welcome Screen...");
        }
    }
}
