package com.app.marketplace;

import java.util.ArrayList;

public class Product {
    int id;
    String productName;
    double price;
    ArrayList<String> productsBuyer = new ArrayList<String>();

    Product(int id, String productName, double price) {
        this.id = id;
        this.price = price;
        this.productName = productName;
    }

    public String toString(){
        return this.id + " " + this.productName + " " + this.price;
    }
}
