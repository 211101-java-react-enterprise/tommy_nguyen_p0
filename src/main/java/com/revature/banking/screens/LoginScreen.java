package com.revature.banking.screens;

import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.models.AppUser;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import javax.naming.AuthenticationException;
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
            userService.authenticateUser(username, password);
            router.navigate("/dashboard");
        } catch (InvalidRequestException | AuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }
}
