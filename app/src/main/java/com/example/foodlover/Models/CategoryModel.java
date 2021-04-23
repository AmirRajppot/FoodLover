package com.example.foodlover.Models;

public class CategoryModel {
    int Id,Img;
    String Name;

    public CategoryModel(int id, int img, String name) {
        Id = id;
        Img = img;
        Name = name;
    }

    public CategoryModel(int img, String name) {
        Img = img;
        Name = name;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
