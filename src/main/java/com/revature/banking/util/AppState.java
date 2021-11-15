package com.revature.banking.util;


import com.revature.banking.daos.AppUserDAO;
import com.revature.banking.models.AppUser;
import com.revature.banking.screens.DashboardScreen;
import com.revature.banking.screens.LoginScreen;
import com.revature.banking.screens.RegisterScreen;
import com.revature.banking.screens.WelcomeScreen;
import com.revature.banking.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    This is our encapsulation of application state. we will create a few things in  here that will used throughout the
    application:
        - a common BufferedReader that all screens can use to read from the console
        - a ScreenRouter that can be used to navigate from one screen to another.
        - a boolean that indicates if the app is still running or not.
 */
public class AppState {
    private static boolean appRunning;
    private final ScreenRouter router;

    public AppState() {
        appRunning = true;
        router = new ScreenRouter();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        AppUserDAO userDAO = new AppUserDAO();
        UserService userService = new UserService(userDAO);
        //TODO: add other screens to router (Login, Welcome, Dashboard)
        router.addScreen(new WelcomeScreen(reader, router));
        router.addScreen(new RegisterScreen(reader, router, userService));
        router.addScreen(new LoginScreen(reader, router, userService));
        router.addScreen(new DashboardScreen(reader, router, userService));
    }

    public void startup() {
        try {
            while (appRunning) {
                router.navigate("/welcome");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        appRunning = false;
    }
}