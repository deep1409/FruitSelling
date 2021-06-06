package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.internship.myapplication.Adapter.Cart_Adapter;
import com.internship.myapplication.Adapter.Order_Adapter;
import com.internship.myapplication.pojo.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class Orders extends AppCompatActivity {
    ImageView back_arrow;
    List<OrderModel> order_item;
    Order_Adapter Adapter;
    RecyclerView recyclerview_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        back_arrow = findViewById(R.id.back_btn_cart);
        recyclerview_order = findViewById(R.id.recyclerview_order);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        order_item = new ArrayList<>();
        order_item.add(new OrderModel("Lemon","https://i.ndtvimg.com/mt/cooks/2014-11/lemon.jpg","2","50","Delivered","06-06-2021"));
        order_item.add(new OrderModel("Guava","https://www.santosfood.com/wp-content/uploads/2020/01/4-4.jpg","2","50","Delivered","06-06-2021"));
        order_item.add(new OrderModel("Custard apple","https://www.parasperfumers.com/upload/product_ecom/Custard-Apple-Seed-Oil.jpg","2","50","Delivered","06-06-2021"));
        order_item.add(new OrderModel("Watermelon","https://cdn.britannica.com/99/143599-050-C3289491/Watermelon.jpg","2","50","Delivered","06-06-2021"));
        order_item.add(new OrderModel("Grapes","https://www.aicr.org/wp-content/uploads/2020/01/shutterstock_533487490-640x462.jpg","2","50","Delivered","06-06-2021"));
        order_item.add(new OrderModel("Apple","https://static.libertyprim.com/files/familles/pomme-large.jpg?1569271834","2","50","Delivered","06-06-2021"));
        order_item.add(new OrderModel("Blackberry","https://4.imimg.com/data4/HR/HD/MY-2312690/blackberry-fruit-500x500.jpg","2","50","Delivered","06-06-2021"));
        order_item.add(new OrderModel("Orange","https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg","2","50","Delivered","06-06-2021"));

        Adapter = new Order_Adapter(Orders.this,order_item);
        recyclerview_order.setLayoutManager(new LinearLayoutManager(this));
        recyclerview_order.setAdapter(Adapter);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

