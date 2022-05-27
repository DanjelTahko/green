package com.example.green;

import com.example.green.Database.GreenDB;
import com.example.green.Model.Climate;

import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        GreenDB db = new GreenDB();
        List<Climate> array = db.getAllArrayData();
        for(int i = 0; i < array.size(); i++) {
            int day = array.get(i).getDate().getDate();
            System.out.println(day);
        }
    }
}
