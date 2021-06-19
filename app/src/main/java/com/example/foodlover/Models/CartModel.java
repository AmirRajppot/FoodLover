package com.example.foodlover.Models;

public class CartModel {
    int p_id,  d_id,p_price, d_price;
    int qty = 1;
    String p_img,d_img, p_name,  d_name;

    public CartModel(int p_id, int d_id, int p_price, int d_price, String p_img, String d_img, String p_name, String d_name) {
        this.p_id = p_id;
        this.d_id = d_id;
        this.p_price = p_price;
        this.d_price = d_price;
        this.p_img = p_img;
        this.d_img = d_img;
        this.p_name = p_name;
        this.d_name = d_name;
    }

    public CartModel(int p_id, int p_price, int d_id, int d_price, int qty, String p_img, String p_name, String d_img, String d_name, String d_des) {
        this.p_id = p_id;
        this.p_price = p_price;
        this.d_id = d_id;
        this.d_price = d_price;
        this.qty = qty;
        this.p_img = p_img;
        this.p_name = p_name;
        this.d_img = d_img;
        this.d_name = d_name;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getP_price() {
        return p_price;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public int getD_price() {
        return d_price;
    }

    public void setD_price(int d_price) {
        this.d_price = d_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getP_img() {
        return p_img;
    }

    public void setP_img(String p_img) {
        this.p_img = p_img;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getD_img() {
        return d_img;
    }

    public void setD_img(String d_img) {
        this.d_img = d_img;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }


}