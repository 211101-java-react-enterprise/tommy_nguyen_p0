package com.revature.banking.screens;

import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen{
    public LoginScreen(BufferedReader reader, ScreenRouter router) {
        super("LoginScreen", "/login", reader, router);
    }
    @Override
    public void render() {

    }
}
