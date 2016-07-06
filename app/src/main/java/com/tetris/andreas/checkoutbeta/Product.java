package com.tetris.andreas.checkoutbeta;

/**
 * Created by ANDREAS on 30.06.2016.
 */
public class Product {

    private String name;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    private String imgURL = "http://www.bbcgoodfood.com/sites/default/files/glossary/banana-crop.jpg";

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
        return name + " " + String.format("%.2f",price) + "€";
    }





}
