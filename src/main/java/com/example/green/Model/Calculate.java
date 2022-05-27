package com.example.green.Model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.round;

public class Calculate {

    public Calculate(){}

    public Climate calculate(int id, double temp, double hum, String area, Timestamp date) {
        // Round double with two decimals
        double new_temp = Math.round((temp / 3) * 100.0) / 100.0;
        double new_hum = Math.round((hum / 3) * 100.0) / 100.0;
        // Create new climate with rounded data
        Climate new_climate = new Climate(id, new_temp, new_hum, area, date);
        return new_climate;

    }

    public List<Climate> getAverage(List<Climate> array) {
        List<Climate> average_array = new ArrayList<>();
        double temp = 0;
        double hum = 0;
        int id = 0;
        int date = array.get(0).getDate().getDate();

        for (int i = 0; i < array.size(); i++) {

            int temp_date = array.get(i).getDate().getDate();
            // if date is not same as the date from List
            if (date != temp_date) {
                Climate new_climate = calculate(id, temp, hum, array.get(i).getArea(), array.get(i-1).getDate());
                average_array.add(new_climate);
                temp = 0;
                hum = 0;
                id ++;
                date = array.get(i).getDate().getDate();
            }
            temp += array.get(i).getTemperature();
            hum += array.get(i).getHumidity();
        }
        // adds last average temp & hum into average_array
        Climate last_climate = calculate(id +1,temp, hum, array.get(0).getArea(), array.get(array.size() -1).getDate());
        average_array.add(last_climate);

        return average_array;
    }

}

