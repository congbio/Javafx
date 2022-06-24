package com.example.hotelmanagement;

public class customers {
    public int cusID;
    public String cusName, email, address, password;

    public customers(int cusID, String cusName, String email, String address, String password) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public customers(String cusName, String email, String address, String password) {
        this.cusName = cusName;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}