package com.revature.banking.services;

import com.revature.banking.daos.AccountDAO;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.Account;
import com.revature.banking.models.AppUser;

public class AccountService {

    private AccountDAO accountDAO;
    private Account sessionAccount;

    public AccountService(AccountDAO accountDAO) {
        this.sessionAccount = null;
        this.accountDAO = accountDAO;
    }

    public Account getSessionAccount() {
        return sessionAccount;
    }

    public boolean isAccountValid(Account newAccount) {
        if (newAccount == null) return false;
        return (newAccount.getAccountType() != null && !newAccount.getAccountType().trim().equals(""));
    }

    public boolean createNewCheckingAccount(Account newCheckingAccount) {
        if (!isAccountValid(newCheckingAccount)) {
            throw new InvalidRequestException("Invalid user data provide.");
        }

        boolean checkingAvailable = accountDAO.findAccountTypeByAccountOwnerId(newCheckingAccount.getAccountOwner_Id()) == null;

        if(!checkingAvailable) {
            throw new ResourcePersistenceException("User already has a checking account made");
        }

        Account createdCheckingAccount = accountDAO.save(newCheckingAccount);
        if (createdCheckingAccount == null) {
            throw new ResourcePersistenceException("The checking account could not be persisted to the datasource!");
        }
        return true;
    }

    public boolean createNewSavingsAccount(Account newSavingsAccount) {
        if (!isAccountValid(newSavingsAccount)) {
            throw new InvalidRequestException("Invalid user data provide.");
        }

        boolean checkingAvailable = accountDAO.findAccountTypeByAccountOwnerId(newSavingsAccount.getAccountOwner_Id()) == null;

        if(!checkingAvailable) {
            throw new ResourcePersistenceException("User already has a savings account made");
        }

        Account createdSavingsAccount = accountDAO.save(newSavingsAccount);
        if (createdSavingsAccount == null) {
            throw new ResourcePersistenceException("The savings account could not be persisted to the datasource!");
        }
        return true;
    }
}
