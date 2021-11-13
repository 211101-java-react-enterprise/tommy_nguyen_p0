package com.revature.banking.services;

import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.models.AppUser;

import java.io.File;
import java.io.FileWriter;

public class UserService {


    public boolean registerNewUser(AppUser newUser) {
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provide.");
        }

        //TODO: write logic that verifies that the new user's username and email are not already taken

        //TODO: Need to move to DAO Pattern Logic
        try {
            File usersFile = new File("resources/data.txt");
            FileWriter fileWriter = new FileWriter(usersFile, true);
            fileWriter.write(newUser.toFileString() + "\n");
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //TODO: Implement
    public AppUser authenicateUser(String username, String password) {
        return null;
    }

    //TODO: Implement accordingly to BANK (figure out what needs to be included in registration)
    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("") || !user.getEmail().contains("@") || !user.getEmail().contains(".")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return (user.getPassword() != null && !user.getPassword().trim().equals(""));
    }
}

