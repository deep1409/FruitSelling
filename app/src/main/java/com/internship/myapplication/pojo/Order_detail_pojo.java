package com.internship.myapplication.pojo;

public class Order_detail_pojo {

    String order_id;
    String item_id;
    String item_quantity;
    String item_prize;
    String item_name;
    String item_url;

    public Order_detail_pojo() {
    }

    public Order_detail_pojo(String order_id, String item_id, String item_quantity, String item_prize,String item_name, String item_url) {
        this.order_id = order_id;
        this.item_id = item_id;
        this.item_quantity = item_quantity;
        this.item_prize = item_prize;
        this.item_name = item_name;
        this.item_url = item_url;
    }
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItem_prize() {
        return item_prize;
    }

    public void setItem_prize(String item_prize) {
        this.item_prize = item_prize;
    }
}
