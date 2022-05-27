package com.example.green.Model;

import java.sql.Timestamp;
import java.util.Date;

public class Climate {

    int id;
    double temperature;
    double humidity;
    String area;
    Timestamp date; /// ?????

    public Climate() {}

    public Climate(int id, double temperature, double humidity, String area, Timestamp date) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.area = area;
        this.date = date;
    }

    public String getString() {
        String foo = "id: " + this.id + ", temp: " + this.temperature + ", hum: " + this.humidity + ", area: " + this.area + ", date: " + this.date;
        return foo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
