package com.example.foodlover.Models;

import java.util.ArrayList;

public class DealModel {
    int Id, Img, Price;
    String Name, des;
    ArrayList list;

    public DealModel(int id, int img, int price, String name, String des) {
        Id = id;
        Img = img;
        Price = price;
        Name = name;
        this.des = des;
    }

    public DealModel(int id, int img, int price, String name, String des, ArrayList list) {
        Id = id;
        Img = img;
        Price = price;
        Name = name;
        this.des = des;
        this.list = list;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }
}
