package com.buyusbyus;
public class Product {
    private int id;
    private String title;
    private String shortdesc;
    private double promotion;
    private double price;
    private String image;

    public Product(int id, String title, String shortdesc, double promotion, double price, String image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.promotion = promotion;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getPromotion() {
        return promotion;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}