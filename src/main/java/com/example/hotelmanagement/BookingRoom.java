package com.example.hotelmanagement;

public class BookingRoom {
    protected int idbook;
    protected String username;
    protected String email;

    protected String phoneNumber;
    protected String dateInput,dateOutput;

    protected int idRoom;


    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public String getDateOutput() {
        return dateOutput;
    }

    public void setDateOutput(String dateOutput) {
        this.dateOutput = dateOutput;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public BookingRoom(int idbook, String username, String email, String phoneNumber, String dateInput, String dateOutput, int idRoom) {
        this.idbook = idbook;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateInput = dateInput;
        this.dateOutput = dateOutput;
        this.idRoom = idRoom;
    }

    public BookingRoom(String username, String email, String phoneNumber, String dateInput, String dateOutput, int idRoom) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateInput = dateInput;
        this.dateOutput = dateOutput;
        this.idRoom = idRoom;
    }




}
