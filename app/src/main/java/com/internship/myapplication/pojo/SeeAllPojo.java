package com.internship.myapplication.pojo;

public class SeeAllPojo {

    String id,item_name,item_price,item_url;

    public SeeAllPojo(String id,String item_name, String item_price, String item_url) {
        this.id = id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_url = item_url;
    }

    public SeeAllPojo (){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }
}
