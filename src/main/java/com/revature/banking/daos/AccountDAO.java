package com.revature.banking.daos;

import com.revature.banking.models.Account;
import com.revature.banking.models.AppUser;
import com.revature.banking.util.collections.List;
import com.revature.banking.util.datasource.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AccountDAO implements CrudDAO<Account>{
    @Override
    public Account save(Account newAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            newAccount.setId(UUID.randomUUID().toString());

            String sql = "insert into user_accounts (account_id, account_type, account_owner_id, account_balance) values (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newAccount.getId());
            pstmt.setString(2, newAccount.getAccountType());
            pstmt.setString(3, newAccount.getAccountOwner_Id());
            pstmt.setDouble(4, newAccount.getAccountBalance());


            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return newAccount;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //why does this have to be static? does it mess anything else up?
    public static Account findAccountTypeByAccountOwnerId(String accountOwnerID) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from app_users where accountOwnerID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountOwnerID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setAccountType(rs.getString("account_type"));
                return account;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
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
