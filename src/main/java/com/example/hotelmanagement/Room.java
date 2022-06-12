package com.example.hotelmanagement;

public class Room {
    public int id;
    public String name;
    public float price;
    public String description;
//    public String image;
//// contructor add image
//    public Room(int id, String name, float price, String description, String image) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//        this.image = image;
//    }

    public Room(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Room(int id, String name, float price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
