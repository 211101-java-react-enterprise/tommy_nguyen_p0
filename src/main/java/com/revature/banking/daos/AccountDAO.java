package com.revature.banking.daos;

import com.revature.banking.models.Account;
import com.revature.banking.util.collections.List;

public class AccountDAO implements CrudDAO<Account>{
    @Override
    public Account save(Account newObj) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account findById(String id) {
        return null;
    }

    @Override
    public boolean update(Account updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }
}
