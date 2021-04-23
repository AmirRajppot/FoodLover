package com.example.foodlover.Models;

public class ProductModel {
    int Id,Price, Img;
    String Name,description,quantity,is_deal;

    public ProductModel(int id, int price, int img, String name) {
        Id = id;
        Price = price;
        Img = img;
        Name = name;
    }

    public ProductModel(int id, int price, int img, String name, String description) {
        Id = id;
        Price = price;
        Img = img;
        Name = name;
        this.description = description;
    }

    public ProductModel(int id, int price, int img, String name, String description, String quantity, String is_deal) {
        Id = id;
        Price = price;
        Img = img;
        Name = name;
        this.description = description;
        this.quantity = quantity;
        this.is_deal = is_deal;
    }

    public ProductModel(int id, int price, int img, String name, String description, String quantity) {
        Id = id;
        Price = price;
        Img = img;
        Name = name;
        this.description = description;
        this.quantity = quantity;
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

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIs_deal() {
        return is_deal;
    }

    public void setIs_deal(String is_deal) {
        this.is_deal = is_deal;
    }
}
