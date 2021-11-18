package com.revature.banking.screens;

import com.revature.banking.exceptions.InvalidFormatException;
import com.revature.banking.exceptions.OverdraftException;
import com.revature.banking.models.AppUser;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class WithdrawScreen extends Screen {
    private final UserService userService;


    public WithdrawScreen(BufferedReader reader, ScreenRouter router, UserService userService) {
        super("WithdrawScreen", "/withdraw", reader, router);
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
            System.out.println("How much would you like to withdraw from your account?");
            String withdrawAmount = reader.readLine();

            try {
                userService.withdraw(sessionUser, withdrawAmount);
                logger.log("Successful withdrawal: %s at %d", withdrawAmount, System.currentTimeMillis());
                router.navigate("/account");
            } catch (InvalidFormatException | OverdraftException e) {
                System.out.println(e.getMessage());
                logger.log("Unsuccessful withdrawal: attempted amount: %s at %d", withdrawAmount, System.currentTimeMillis());
                router.navigate("/account");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
