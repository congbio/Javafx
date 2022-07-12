package com.example.hotelmanagement;

public class customers {
    private int cusID;

    private int getCusID() {
        return cusID;
    }

    private void setCusID(int cusID) {
        this.cusID = cusID;
    }

    private String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String cusName, email, password;

    public customers(int cusID, String cusName, String email,   String password) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.email = email;
        this.password = password;
    }

    public customers(String cusName, String email,   String password) {
        this.cusName = cusName;
        this.email = email;
        this.password = password;
    }


}