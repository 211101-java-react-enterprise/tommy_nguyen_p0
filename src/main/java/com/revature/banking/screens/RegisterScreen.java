package com.revature.banking.screens;

import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.AppUser;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    // why not in screen class? well do all screens need a userService???
    private final UserService userService;

    public RegisterScreen(BufferedReader reader, ScreenRouter router, UserService userService) {
        super("RegisterScreen", "/register", reader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("The user selected Register");
        System.out.println("Please provide us with some basic information");

        System.out.print("First name: ");
        String firstName = reader.readLine();
        System.out.print("Last name: ");
        String lastName = reader.readLine();
        System.out.print("Email: ");
        String email = reader.readLine();
        System.out.print("Username: ");
        String username = reader.readLine();
        System.out.print("Password: ");
        String password = reader.readLine();

        double balance = 0;

        AppUser newUser = new AppUser(firstName, lastName, email, username, password, balance);
        try {
            userService.registerNewUser(newUser);
            router.navigate("/account");
        } catch (InvalidRequestException | ResourcePersistenceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            //TODO log this unexpected exception to a file
            e.printStackTrace();
        }
    }

}

