package com.revature.banking.services;

import com.revature.banking.daos.AppUserDAO;
import com.revature.banking.exceptions.InvalidFormatException;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.OverdraftException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.AppUser;

import javax.naming.AuthenticationException;

public class UserService {

    private final AppUserDAO userDAO;
    private AppUser sessionUser;

    public UserService(AppUserDAO userDAO) {
        this.sessionUser = null;
        this.userDAO = userDAO;
    }

    public AppUser getSessionUser() {
        return sessionUser;
    }

    public boolean registerNewUser(AppUser newUser) {
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provide.");
        }

        boolean usernameAvailable = userDAO.findUserByUsername(newUser.getUsername()) == null;
        boolean emailAvailable = userDAO.findUserByEmail(newUser.getEmail()) == null;
        if (!usernameAvailable || !emailAvailable) {
            if (!usernameAvailable && emailAvailable) {
                throw new ResourcePersistenceException("The provided username was already taken in the datasource!");
            } else if (usernameAvailable) {
                throw new ResourcePersistenceException("The provided email was already taken in the datasource!");
            } else {
                throw new ResourcePersistenceException("The provided username and email was already taken in the datasource!");
            }
        }

        AppUser registeredUser = userDAO.save(newUser);
        if (registeredUser == null) {
            throw new ResourcePersistenceException("The user could not be persisted to the datasource!");
        }
        return true;
    }

    public void authenticateUser(String username, String password) throws AuthenticationException {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid Credentials Provided");
        }

        AppUser authenticatedUser = userDAO.findUserByUsernameAndPassword(username, password);

        if (authenticatedUser == null) {
            throw new AuthenticationException();
        }

        sessionUser = authenticatedUser;
    }

    public void logout() {
        sessionUser = null;
    }

    public boolean isSessionActive() {
        return sessionUser != null;
    }

    //TODO: Implement accordingly to BANK (figure out what needs to be included in registration) make sure it matches the requirements on database
    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("") || !user.getEmail().contains("@") || !user.getEmail().contains("."))
            return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return (user.getPassword() != null && !user.getPassword().trim().equals(""));
    }

    public boolean deposit(AppUser appUser, String value) {
        //If Empty String Throw Exception
        if (value == null || value.trim().equals("")) {
            throw new InvalidFormatException("Please enter a numeric value in the proper currency format xxxxxxx.xx");
        }
        //If Contains Letters Throw Exception
        if (containLetters(value)) {
            throw new InvalidFormatException("Please only use numbers and as a value in the proper currency format xxxxxxx.xx");
        }
        //If Contains More Than 2 Decimal Place Throw Exception
        int decimalLength = (value.length() - 1) - value.indexOf(".");
        if (decimalLength > 2) {
            throw new InvalidFormatException("Please enter amount in the proper currency format xxxxxxx.xx");
        }
        double depositAmount = Double.parseDouble(value);
        //If Withdraw Amount Is Negative Throw Exception
        if (depositAmount < 0) {
            throw new InvalidFormatException("Please enter a positive number in the proper currenct format xxxxxxx.xx");
        }
        appUser.setBalance(appUser.getBalance() + depositAmount);
        return userDAO.updateBalance(appUser);
    }

    public boolean withdraw(AppUser appUser, String value) {
        //If Empty String Throw Exception
        if (value == null || value.trim().equals("")) {
            throw new InvalidFormatException("Please enter a numeric value in the proper currency format xxxxxxx.xx");
        }
        //If Contains Letters Throw Exception
        if (containLetters(value)) {
            throw new InvalidFormatException("Please only use numbers and as a value in the proper currency format xxxxxxx.xx");
        }
        //If Contains More Than 2 Decimal Place Throw Exception
        int decimalLength = (value.length() - 1) - value.indexOf(".");
        if (decimalLength > 2) {
            throw new InvalidFormatException("Please enter amount in the proper currency format xxxxxxx.xx");
        }
        double withdrawAmount = Double.parseDouble(value);
        //If Withdraw Amount Is Negative Throw Exception
        if (withdrawAmount < 0) {
            throw new InvalidFormatException("Please enter a positive number in the proper currenct format xxxxxxx.xx");
        }
        //If Withdraw Amount Is more Than Account Balance Throw Exception
        if (withdrawAmount > sessionUser.getBalance()) {
            throw new OverdraftException("Withdraw amount is greater than account balance. Overdraft not allowed");
        }
        appUser.setBalance(appUser.getBalance() - withdrawAmount);
        return userDAO.updateBalance(appUser);
    }

    public boolean containLetters(String value) {
        char[] chars = value.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}

