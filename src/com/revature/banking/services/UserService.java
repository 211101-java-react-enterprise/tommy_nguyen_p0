package com.revature.banking.services;

import com.revature.banking.daos.AppUserDAO;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.AppUser;

import javax.naming.AuthenticationException;

public class UserService {

    private AppUserDAO userDAO = new AppUserDAO();
    private AppUser sessionUser;

    public UserService(AppUser sessionUser) {
        this.sessionUser = sessionUser;
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
}

