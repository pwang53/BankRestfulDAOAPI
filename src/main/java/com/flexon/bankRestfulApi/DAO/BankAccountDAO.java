package com.flexon.bankRestfulApi.DAO;

import com.flexon.bankRestfulApi.Model.BankAccount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class BankAccountDAO {
    private static final String IP = "35.153.50.120"; //"localhost";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String PORT = "3306";
    private static final String TABLE = "bank";
    private static final String EXTENSION = "allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DB_URI = "jdbc:mysql://" + IP + ":" + PORT + "/" + TABLE + "?" + EXTENSION;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public BankAccountDAO() {
    }

    public boolean createAccount(BankAccount account) throws SQLException {
        String query = "INSERT INTO accounts VALUES (?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        int success = -1;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URI, USERNAME, PASSWORD);
            ps = conn.prepareStatement(query);
            ps.setString(1, account.getAccountID());
            ps.setBigDecimal(2, account.getBalance());
            ps.setString(3, account.getCustomerID());
            success = ps.executeUpdate();
            System.out.println(success);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success > 0;
    }

    public boolean deleteAccount(String id) {
        String query = "DELETE FROM accounts WHERE account_ID=?";
        Connection conn = null;
        PreparedStatement ps = null;
        int success = 0;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URI, USERNAME, PASSWORD);
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            success = ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success > 0;
    }

    public BankAccount getAccountByID(String id) {
        String query = "SELECT * FROM accounts WHERE account_ID=?";
        Connection conn = null;
        PreparedStatement ps = null;
        BankAccount account = new BankAccount();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URI, USERNAME, PASSWORD);
            ps = conn.prepareStatement(query);
            ps.setString(1,  id);
            System.out.println(ps);
            ResultSet res = ps.executeQuery();
            while(res.next()) {
                account.setAccountID(res.getString("account_ID"));
                account.setBalance(res.getBigDecimal("account_balance"));
                account.setCustomerID(res.getString("customer_ID"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return account;
    }

    public boolean updateAccount(String option, BankAccount account) {
        if (!option.toLowerCase().equals("balance") && !option.toLowerCase().equals("customerid"))
            return false;

        String query;
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URI, USERNAME, PASSWORD);
            if (option.toLowerCase().equals("balance")) {
                if (account.getBalance() == null)
                    return false;
                query = "UPDATE accounts SET account_balance=? WHERE account_ID=?";
                ps = conn.prepareStatement(query);
                ps.setBigDecimal(1, account.getBalance());
            }

            if (option.toLowerCase().equals("customerid")){
                if (account.getCustomerID() == null)
                    return false;
                query = "UPDATE accounts SET customer_ID=? WHERE account_ID=?";
                ps = conn.prepareStatement(query);
                ps.setString(1, account.getCustomerID());
            }
            ps.setString(2, account.getAccountID());
            System.out.println(ps.toString());
            success = ps.executeUpdate() > 0;
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
}
