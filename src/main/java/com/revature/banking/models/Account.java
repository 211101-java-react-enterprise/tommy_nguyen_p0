package com.revature.banking.models;

import java.util.Objects;

public class Account {

    private String id;
    private String accountType;
    private String accountOwner_Id;
    private Double accountBalance;

    public Account(String accountType, AppUser accountOwner, Double accountBalance) {
        this.accountType = accountType;
        this.accountOwner_Id = accountOwner.getId();
        this.accountBalance = accountBalance;
    }

    public Account() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountOwner_Id() {
        return accountOwner_Id;
    }

    public void setAccountOwner_Id(String accountOwner_Id) {
        this.accountOwner_Id = accountOwner_Id;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(accountType, account.accountType) && Objects.equals(accountOwner_Id, account.accountOwner_Id) && Objects.equals(accountBalance, account.accountBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountType, accountOwner_Id, accountBalance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountOwner=" + accountOwner_Id +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
