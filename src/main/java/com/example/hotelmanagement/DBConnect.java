package com.example.hotelmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnect {
    public Connection con;

    public DBConnect() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/db_hotel", "root", "");
            System.out.println("Success");
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
        }

    }

    public ArrayList<Room> getRoom() {
        ArrayList<Room> list = new ArrayList();
        String sql = "SELECT * FROM room";

        try {
            ResultSet result = this.con.prepareStatement(sql).executeQuery();

            while (result.next()) {
                Room Room = new Room(result.getInt("id"), result.getString("name"), result.getFloat("price"), result.getString("description"), result.getString("image"));
                list.add(Room);
            }
            return list;
        } catch (SQLException var5) {
            throw new RuntimeException(var5);
        }
    }


    public void insertRoom(Room rm) {
        String sql = "INSERT INTO room (name, price,description,image) VALUE ('" + rm.name + "','" + rm.price + "','" + rm.description + "','" + rm.image + "')";


        try {
            this.con.prepareStatement(sql).executeUpdate();

        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }

    public void updateRoom(Room rm) {
        String sql = "UPDATE room SET name ='" + rm.name + "', price =" + rm.price + ", description ='" + rm.description + "', image= '" + rm.image + "' WHERE id = " + rm.id;
System.out.println(sql);

            try {
                this.con.prepareStatement(sql).executeUpdate();

            } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }

    public void deleteRoom(int id) {
        String sql = "DELETE FROM room WHERE id = " + id;


        try {
            this.con.prepareStatement(sql).executeUpdate();

        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }


    public List<Admin> getAdmin() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            var result = this.con.prepareStatement("Select * from admin").executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("username");
                String password = result.getString("pass");
                System.out.println(id);
                System.out.println(name);
                System.out.println(password);
                admins.add(new Admin(id, name, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }

}