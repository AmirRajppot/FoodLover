package com.example.foodlover.Models;

public class CategoryModel {
    int Id;
    String Img, Name;

    public CategoryModel(int id, String img, String name) {
        Id = id;
        Img = img;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
