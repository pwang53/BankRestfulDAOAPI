package com.flexon.bankRestfulApi.Model;

public class BankCustomer {
    private String customer_ID;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;

    public BankCustomer(String customer_ID, String first_name, String last_name,String phone, String email) {
        this.customer_ID = customer_ID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
    }

    public void setCustomerID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomerID() {
        return customer_ID;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "BankCustomer [customerID=" + customer_ID +
                ", firstname=" + first_name +
                ", lastname=" + last_name +
                ", phone=" + phone +
                ", email=" + email + "]";
    }
}
