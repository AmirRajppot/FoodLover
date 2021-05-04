package com.example.foodlover.Models;

public class ProductModel {
    int Id,Price,is_famous;
    String Name,description,quantity,Img;

    public ProductModel(int id, int price, String name, String description, String img) {
        Id = id;
        Price = price;
        Name = name;
        this.description = description;
        Img = img;
    }

    public ProductModel(int id, int price, int is_famous, String name, String description, String img) {
        Id = id;
        Price = price;
        this.is_famous = is_famous;
        Name = name;
        this.description = description;
        Img = img;
    }

    public ProductModel(int id, int price, int is_famous, String name, String description, String quantity, String img) {
        Id = id;
        Price = price;
        this.is_famous = is_famous;
        Name = name;
        this.description = description;
        this.quantity = quantity;
        Img = img;
    }

    public ProductModel(int id, int price, String name, String description, String quantity, String img) {
        Id = id;
        Price = price;
        Name = name;
        this.description = description;
        this.quantity = quantity;
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

    public int getIs_famous() {
        return is_famous;
    }

    public void setIs_famous(int is_famous) {
        this.is_famous = is_famous;
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

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}
