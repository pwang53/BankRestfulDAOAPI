package com.flexon.bankRestfulApi.Model;

import java.math.BigDecimal;

public class BankAccount {
    private String account_ID;
    private BigDecimal balance;
    private String customer_ID;

    public BankAccount(String account_ID, BigDecimal balance, String customer_ID) {
        this.account_ID = account_ID;
        this.balance = balance;
        this.customer_ID = customer_ID;
    }

    public BankAccount() {
        this.account_ID = null;
        this.balance = null;
        this.customer_ID = null;
    }

    public String getAccountID() {
        return account_ID;
    }

    public void setAccountID(String account_ID) {
        this.account_ID = account_ID;
    }

    public String getCustomerID() {
        return customer_ID;
    }

    public void setCustomerID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount [accountID=" + account_ID +
                ", balance=" + balance +
                ", customerID=" + customer_ID + "]";
    }
}
