package com.revature.banking;

import com.revature.banking.util.AppState;

import java.io.*;

public class BankingDriver {
    /*
     * User Stories:
     * as a user I can
     *      - register a new user account with the system (must be secured with a password)
     *      - login with my existing credentials
     *      - deposit funds into an account (use doubles, not ints)
     *      - withdraw funds from an account (no overdrafting!)
     *      - view the balance of my account(s) (all balance displays must be in proper currency format)
     *
     * Suggested Bonus User Stories:
     * as a user I can
     *      - view the transaction history for an account
     *      - create multiple accounts per user (checking, savings, etc.)
     *      - share a joint account with another user
     *      - transfer money between accounts
     */

    public static void main(String[] args) {
        AppState app = new AppState();
        app.startup();
    }
}