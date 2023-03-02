package com.example.bestselling2;

public class Main {

    public static void main(String[] args) throws Exception {



        Books.Read();

        for (BestSelling best : BestSelling.getAllBestSelling())
            System.out.println(best);



    }
}