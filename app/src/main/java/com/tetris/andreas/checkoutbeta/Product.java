package com.tetris.andreas.checkoutbeta;

/**
 * Created by ANDREAS on 30.06.2016.
 */
public class Product {

    private String name;

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    private double price;

    String productDescription = "Hi, I'm a product =)!";

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " - Lowest price available: " + String.format("%.2f",price) + "€";
    }





}
