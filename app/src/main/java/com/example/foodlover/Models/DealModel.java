package com.example.foodlover.Models;

import java.util.ArrayList;

public class DealModel {
    int id,  price;
    String name,img, des;

    public DealModel(int id, int price, String name, String img, String des) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.img = img;
        this.des = des;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
