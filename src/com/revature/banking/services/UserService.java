package com.revature.banking.services;

import com.revature.banking.daos.AppUserDAO;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.AppUser;

public class UserService {

    private AppUserDAO userDAO = new AppUserDAO();

    public boolean registerNewUser(AppUser newUser) {
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provide.");
        }


        //TODO: write logic that verifies that the new user's username and email are not already taken

        AppUser registeredUser = userDAO.save(newUser);
        if (registeredUser == null) {
            throw new ResourcePersistenceException("The user could not be persisted to the datasource!");
        }

        return true;
    }

    //TODO: split up username and password invalidation to be more specific
    public AppUser authenticateUser(String username, String password) {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid Credentials Provided");
        }
        AppUser authenticatedUser = userDAO.findUserByUsernameAndPassword(username, password);
        if (authenticatedUser != null) {
            return authenticatedUser;
        }
        //create your own authenticated exception here
        throw new RuntimeException("No account found with provided credentials");
    }

    //TODO: Implement accordingly to BANK (figure out what needs to be included in registration) make sure it matches the requirements on database
    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("") || !user.getEmail().contains("@") || !user.getEmail().contains(".")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return (user.getPassword() != null && !user.getPassword().trim().equals(""));
    }
}

