package com.example.foodlover.Models;

public class WishListModel {
    int Id, Price;
    String Name, Img;

    public WishListModel(int id, int price, String name, String img) {
        Id = id;
        Price = price;
        Name = name;
        Img = img;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}