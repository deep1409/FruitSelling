package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Item_description extends AppCompatActivity {

    ImageView item_des_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        item_des_cart = findViewById(R.id.item_des_cart);

        item_des_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item_description.this,Cart.class);
                startActivity(intent);
            }
        });

    }
}