package com.example.green.Database;

import com.example.green.Model.Climate;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GreenDB {

    Connection con = null;

    public GreenDB() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GreenHouse?user=root&password=password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Climate> getAllArrayData() {
        List<Climate> array = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select data.id, temperature, humidity, area.area, date from data join area on data.areaID=area.id ORDER BY data.date");
            while(rs.next()) {
                int id = rs.getInt("id");
                double temp = rs.getDouble("temperature");
                double hum = rs.getDouble("humidity");
                String area = rs.getString("area");
                Timestamp date = rs.getTimestamp("date");
                Climate foo = new Climate(id, temp, hum, area, date);
                //System.out.println("id: " + id + " temp: " + temp + " hum: " + hum + " area : " + area + " date: " + date);
                array.add(foo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    public List<Climate> getAllDataArea(String letter) {
        List<Climate> A_array = new ArrayList<>();

        try {
            PreparedStatement stmnt = con.prepareStatement("select data.id, temperature, humidity, area.area, date from data join area on data.areaID=area.id where area.area=?");
            stmnt.setString(1, letter);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                double temp = rs.getDouble("temperature");
                double hum = rs.getDouble("humidity");
                String area = rs.getString("area");
                Timestamp date = rs.getTimestamp("date");
                Climate foo = new Climate(id, temp, hum, area, date);
                //System.out.println("id: " + id + " temp: " + temp + " hum: " + hum + " area : " + area + " date: " + date);
                A_array.add(foo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return A_array;
    }

    public void setNewPrice(double price) {

        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO price (price) VALUES (?)");
            stmt.setDouble(1, price);
            stmt.executeUpdate();
            System.out.println("New price added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getPrice() {
        List<Double> priceArray = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT price FROM price");
            while(rs.next()) {
                double price = rs.getDouble("price");
                priceArray.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceArray.get(priceArray.size() - 1);
    }
}
