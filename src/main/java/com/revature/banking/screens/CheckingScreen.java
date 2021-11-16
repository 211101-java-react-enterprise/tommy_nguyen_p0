package com.revature.banking.screens;

import com.revature.banking.services.AccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class CheckingScreen extends Screen{

    private final UserService userService;
    private final AccountService accountService;

    public CheckingScreen(BufferedReader reader, ScreenRouter router, UserService userService, AccountService accountService) {
        super("CheckingScreen", "/checking", reader, router);
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public void render() throws Exception {

    }
}
