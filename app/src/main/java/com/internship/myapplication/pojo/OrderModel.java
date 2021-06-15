package com.internship.myapplication.pojo;

public class OrderModel {
    public OrderModel() {
    }

//    String name;
//    String image;
//    String quantity;
    String price;
    String status;
    String date;
    String orderid;

    public OrderModel(String price, String status, String date, String orderid) {
//        this.name = name;
//        this.image = image;
//        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.date = date;
        this.orderid = orderid;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }



    public String getOrderid() {
        return orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
