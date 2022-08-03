package com.app.marketplace;

import java.util.ArrayList;

public class User {
    int id;
    String firstName;
    String lastName;
    double userMoney;
    ArrayList<Product> products = new ArrayList<Product>();

    User(int id, String firstName, String lastName, double userMoney) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userMoney = userMoney;
    }

    public String toString(){
        return this.id + " " + this.firstName + " " + this.lastName + " " + this.userMoney;
    }
}
