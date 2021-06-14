package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.internship.myapplication.Adapter.Cart_Adapter;
import com.internship.myapplication.pojo.CartModel;
import com.internship.myapplication.pojo.CartModel;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    ImageView back_arrow;
    List<CartModel> cart_item;
    Cart_Adapter Adapter;
    RecyclerView recyclerview_cart;
    Button Place_order_button;
    helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        
        back_arrow = findViewById(R.id.back_btn_cart);
        Place_order_button = findViewById(R.id.Place_order_button);
        recyclerview_cart = findViewById(R.id.recyclerview_cart);

        helper = new helper(Cart.this);

        Place_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this,invoice.class);
                startActivity(intent);
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        cart_item = new ArrayList<>();
//        cart_item.add(new CartModel("Lemon","50","https://i.ndtvimg.com/mt/cooks/2014-11/lemon.jpg","2"));
//        cart_item.add(new CartModel("Guava","50","https://www.santosfood.com/wp-content/uploads/2020/01/4-4.jpg","2"));
//        cart_item.add(new CartModel("Custard apple","50","https://www.parasperfumers.com/upload/product_ecom/Custard-Apple-Seed-Oil.jpg","2"));
//        cart_item.add(new CartModel("Watermelon","50","https://cdn.britannica.com/99/143599-050-C3289491/Watermelon.jpg","2"));
//        cart_item.add(new CartModel("Grapes","50","https://www.aicr.org/wp-content/uploads/2020/01/shutterstock_533487490-640x462.jpg","2"));
//        cart_item.add(new CartModel("Apple","50","https://static.libertyprim.com/files/familles/pomme-large.jpg?1569271834","2"));
//        cart_item.add(new CartModel("Blackberry","50","https://4.imimg.com/data4/HR/HD/MY-2312690/blackberry-fruit-500x500.jpg","2"));
//        cart_item.add(new CartModel("Orange","50","https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg","2"));

        cart_item = helper.getDataFormliteDB();

        Adapter = new Cart_Adapter(Cart.this,cart_item);
        recyclerview_cart.setLayoutManager(new LinearLayoutManager(this));
        recyclerview_cart.setAdapter(Adapter);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}