package com.example.foodlover.Models;

public class CartModel {
    int id, price;
    int qty = 1;
    String img, name;

    public CartModel(int id, int price, String img, String name) {
        this.id = id;
        this.price = price;
        this.img = img;
        this.name = name;
    }

    public CartModel(int id, int price, int qty, String img, String name) {
        this.id = id;
        this.price = price;
        this.qty = qty;
        this.img = img;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
