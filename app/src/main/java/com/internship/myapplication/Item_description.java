package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Item_description extends AppCompatActivity {

    ImageView item_des_cart,item_img,back_arrow;
    TextView item_name,item_price,item_total_price,item_quantity,item_type;
    Button quan_plus,quan_minus,item_des_order_now;
    Intent i;
    helper helper;
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);


        item_des_order_now = findViewById(R.id.item_des_order_now);
        item_img = findViewById(R.id.item_des_img);
        item_name = findViewById(R.id.item_des_name);
        item_price = findViewById(R.id.item_des_price);
        item_total_price = findViewById(R.id.item_des_total_price);
        back_arrow = findViewById(R.id.item_des_back_arrow);
        quan_plus = findViewById(R.id.item_des_quantity_plus);
        quan_minus = findViewById(R.id.item_des_quantity_minus);
        item_quantity = findViewById(R.id.textView9);

        helper = new helper(Item_description.this);

        i = getIntent();
        Picasso.get().load(i.getStringExtra("img")).into(item_img);
        item_name.setText(i.getStringExtra("name"));
        item_price.setText("₹"+i.getStringExtra("price")+"/kg");
        item_total_price.setText("₹"+i.getStringExtra("price"));
        item_quantity.setText(String.valueOf(counter));
//        Toast.makeText(this, "name : "+i.getStringExtra("name")+"\n"+"price :"+i.getStringExtra("price"), Toast.LENGTH_SHORT).show();

        quan_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter >= 10 || item_quantity.getText().toString().equals(10)){
                    Toast.makeText(Item_description.this, "Sorry!, You can't buy more than 10.", Toast.LENGTH_SHORT).show();
                    counter = 10;
                    item_quantity.setText(String.valueOf(counter));
                    int total = Integer.valueOf(i.getStringExtra("price"));
                    int grant_total = counter*total;
                    String s = String.valueOf(grant_total);
                    item_total_price.setText("₹"+s);
                }
                else {
                    counter++;
                    int total = Integer.valueOf(i.getStringExtra("price"));
                    int grant_total = counter*total;
                    String s = String.valueOf(grant_total);
                    item_total_price.setText("₹"+s);
                    item_quantity.setText(String.valueOf(counter));

                }
            }
        });
        quan_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 1 || item_quantity.getText().toString().equals(0)){
                    Toast.makeText(Item_description.this, "Sorry!, You can't buy less than 1 item.", Toast.LENGTH_SHORT).show();
                    counter = 1;
                    item_quantity.setText(String.valueOf(counter));
                    int total = Integer.valueOf(i.getStringExtra("price"));
                    int grant_total = counter*total;
                    String s = String.valueOf(grant_total);
                    item_total_price.setText(s);
                }
                else {
                    counter--;
                    int total = Integer.valueOf(i.getStringExtra("price"));
                    int grant_total = counter*total;
                    String s = String.valueOf(grant_total);
                    item_total_price.setText(s);
                    item_quantity.setText(String.valueOf(counter));
                }
            }
        });



        item_des_order_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.insert(item_name.getText().toString(),i.getStringExtra("price"), item_quantity.getText().toString(),i.getStringExtra("img"));
                Intent intent = new Intent(Item_description.this,Cart.class);
                startActivity(intent);
            }
        });


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}