package com.example.bestselling2;

import java.io.Serializable;
import java.util.ArrayList;

public class BestSelling implements Serializable {
    private static ArrayList<BestSelling> allBestSelling = new ArrayList<>();

        private  String name;
        private  int year;
        private long sales;


    public BestSelling(String name, int year, long sales) {
        this.name = name;
        this.year = year;
        this.sales = sales;
        allBestSelling.add(this);
    }

    public static ArrayList<BestSelling> getAllBestSelling() {
        return allBestSelling;
    }

    public static void setAllBestSelling(ArrayList<BestSelling> allBestSelling) {
        BestSelling.allBestSelling = allBestSelling;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }
}
