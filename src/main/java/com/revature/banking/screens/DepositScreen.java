package com.revature.banking.screens;

import com.revature.banking.exceptions.InvalidFormatException;
import com.revature.banking.models.AppUser;
import com.revature.banking.screens.Screen;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class DepositScreen extends Screen {
    private final UserService userService;
    //TODO also need account service?

    public DepositScreen(BufferedReader reader, ScreenRouter router, UserService userService) {
        super("DepositScreen", "/deposit", reader, router);
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
            System.out.println("How much would you like to deposit to your account?");
            String depositAmount = reader.readLine();

            try {
                userService.deposit(userService.getSessionUser(), depositAmount);
                logger.log("Successful deposit: %s at %d", depositAmount, System.currentTimeMillis());
                router.navigate("/account");
            } catch (InvalidFormatException e) {
                logger.log("Unsuccessful deposit: attempted amount: %s at %d", depositAmount, System.currentTimeMillis());
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
