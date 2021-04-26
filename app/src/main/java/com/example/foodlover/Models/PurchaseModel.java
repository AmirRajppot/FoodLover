package com.example.foodlover.Models;

import java.util.IdentityHashMap;

public class PurchaseModel {
    int Id, Price, Order_id;
    String date;

    public PurchaseModel(int id, int price, int order_id, String date) {
        Id = id;
        Price = price;
        Order_id = order_id;
        this.date = date;
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

    public int getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(int order_id) {
        Order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
