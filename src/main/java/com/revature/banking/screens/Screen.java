package com.revature.banking.screens;

import com.revature.banking.util.ScreenRouter;
import com.revature.banking.util.logging.Logger;

import java.io.BufferedReader;

public abstract class Screen {

    protected Logger logger = Logger.getLogger(true);
    protected String name;
    protected String route;
    protected BufferedReader reader;
    protected ScreenRouter router;

    public Screen(String name, String route, BufferedReader reader, ScreenRouter router) {
        this.name = name;
        this.route = route;
        this.reader = reader;
        this.router = router;
    }

    public final String getName() {
        return name;
    }
    public final String getRoute() {
        return route;
    }

    public abstract void render() throws Exception;
}
