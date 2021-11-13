package com.revature.banking.screens;

import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;


public class DashboardScreen extends Screen{
    public DashboardScreen(BufferedReader reader, ScreenRouter router) {
        super("DashboardScreen", "/dashboard", reader, router);
    }
    @Override
    public void render() {

    }
}
