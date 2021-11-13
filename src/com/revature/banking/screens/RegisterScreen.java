package com.revature.banking.screens;

import com.revature.banking.models.AppUser;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;

public class RegisterScreen extends Screen {

    // why not in screen class? well do all screens need a userService???
    private UserService userService;

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

        System.out.printf("Provided user info: { \"firstName\": %s, \"lastName\": %s, \"email\": %s, \"username\": %s, \"password\": %s }\n",
                firstName, lastName, email, username, password); //BREAD CRUMB STATEMENT

        AppUser newUser = new AppUser(firstName, lastName, email, username, password);
        boolean registerSuccessful = userService.registerNewUser(newUser);

        if (registerSuccessful) {
            //TODO: naivgate it to dashboard or do something for the bank
        } else {
            System.out.println("You have provided invalid data. Please try again.");
        }




//        String someData = "Wezley:Singleton:wezley.singleton@revature.net:wsingleton:password"; //BREAD CRUMB STATEMENT
//        String[] dataFragments = someData.split(":"); //BREAD CRUMB STATEMENT
//        for (int i = 0; i < dataFragments.length; i++) {
//            System.out.println(dataFragments[i]);
//        }
    }

}
