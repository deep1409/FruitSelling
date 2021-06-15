package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.internship.myapplication.Adapter.Cart_Adapter;
import com.internship.myapplication.Adapter.Order_Adapter;
import com.internship.myapplication.Adapter.SeeAllAdapter;
import com.internship.myapplication.pojo.OrderModel;
import com.internship.myapplication.pojo.SeeAllPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Orders extends AppCompatActivity {
    ImageView back_arrow;
    List<OrderModel> order_item;
    Order_Adapter Adapter;
    RecyclerView recyclerview_order;
    LoadingAnim loadingAnim;
    String result,url;
    Order_Adapter adapter;
    ExecutorService executorService2;

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
        order_item.add(new OrderModel("20","Undelivered","15-06-2021","01"));
        order_item.add(new OrderModel("30","Undelivered","15-06-2021","02"));
//        order_item.add(new OrderModel("Custard apple","https://www.parasperfumers.com/upload/product_ecom/Custard-Apple-Seed-Oil.jpg","2","50","Delivered","06-06-2021"));
//        order_item.add(new OrderModel("Watermelon","https://cdn.britannica.com/99/143599-050-C3289491/Watermelon.jpg","2","50","Delivered","06-06-2021"));
//        order_item.add(new OrderModel("Grapes","https://www.aicr.org/wp-content/uploads/2020/01/shutterstock_533487490-640x462.jpg","2","50","Delivered","06-06-2021"));
//        order_item.add(new OrderModel("Apple","https://static.libertyprim.com/files/familles/pomme-large.jpg?1569271834","2","50","Delivered","06-06-2021"));
//        order_item.add(new OrderModel("Blackberry","https://4.imimg.com/data4/HR/HD/MY-2312690/blackberry-fruit-500x500.jpg","2","50","Delivered","06-06-2021"));
//        order_item.add(new OrderModel("Orange","https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg","2","50","Delivered","06-06-2021"));
//


        Adapter = new Order_Adapter(Orders.this,order_item);
        recyclerview_order.setLayoutManager(new LinearLayoutManager(this));
        recyclerview_order.setAdapter(Adapter);

    }

    public void retrieveFromDB() {
        loadingAnim.startLoadingDialog();
        executorService2.execute(new Runnable() {
            @Override
            public void run() {
                try
                {
                    JsonParser o = new JsonParser();

                    result = o.insert(url);

                    order_item = new ArrayList<>();

                    JSONObject jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("res");

                    Log.v("Login_DATA",""+result);

                    //for fruit
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                        OrderModel p = new OrderModel();

                        p.setOrderid(jsonObject11.getString("order_id"));
                        p.setDate(jsonObject11.getString("order_date"));
                        p.setPrice(jsonObject11.getString("order_price"));
                        p.setStatus(jsonObject11.getString("order_status"));

                        order_item.add(p);


                    }

                }
                catch ( JSONException e)
                {
                    e.printStackTrace();
                    //  Toast.makeText(Login.this, "Please check your Internet Connection and Retry", Toast.LENGTH_LONG).show();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        loadingAnim.dismissDialog();

                        adapter = new Order_Adapter(Orders.this,order_item);
                        recyclerview_order.setAdapter(adapter);

                    }
                });
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Orders.this,Home.class);
        startActivity(intent);
    }
}

