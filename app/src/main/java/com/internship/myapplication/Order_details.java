package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.internship.myapplication.Adapter.Order_Adapter;
import com.internship.myapplication.Adapter.Order_detail_Adapter;
import com.internship.myapplication.pojo.OrderModel;
import com.internship.myapplication.pojo.Order_detail_pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Order_details extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Order_detail_pojo> order_details;
    Order_detail_Adapter order_detail_adapter;
    String header,url,result;
    Intent i;
    String order_id;
    LoadingAnim loadingAnim;
    ExecutorService executorService;
    ImageView back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        recyclerView = findViewById(R.id.recyclerView);
        back_btn = findViewById(R.id.back_btn_order_detail);

        i = getIntent();
        order_id = i.getStringExtra("order_id");

        header = getString(R.string.header);
        url = header+"retrieve_order_details.php?order_id="+order_id;
        Log.d("URL", "onCreate: "+url);
        order_details = new ArrayList<>();

        loadingAnim = new LoadingAnim(Order_details.this);
        executorService = Executors.newSingleThreadExecutor();
        retrieveFromDB();


        order_detail_adapter = new Order_detail_Adapter(Order_details.this,order_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(order_detail_adapter);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }


    public void retrieveFromDB() {
        loadingAnim.startLoadingDialog();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try
                {
                    JsonParser o = new JsonParser();

                    result = o.insert(url);

                    order_details = new ArrayList<>();

//                    JSONObject jsonObject = new JSONObject(result);
//
//                    JSONArray jsonArray = jsonObject.getJSONArray("res");

                    Log.v("order_details_log",""+result);

                    //for fruit
//                    for (int i = 0; i < jsonArray.length(); i++) {
//
//                        JSONObject jsonObject11 = jsonArray.getJSONObject(i);
//                        Order_detail_pojo p = new Order_detail_pojo();
//
//                        p.setItem_id(jsonObject11.getString("item_id"));
//                        p.setItem_quantity(jsonObject11.getString("item_quantity"));
//                        p.setItem_prize(jsonObject11.getString("item_prize"));
//                        p.setItem_name(jsonObject11.getString("item_name"));
//                        p.setItem_url(jsonObject11.getString("item_url"));
//
//                        order_details.add(p);
//
//
//                    }

                }
                catch ( Exception e)
                {
                    e.printStackTrace();
                    //  Toast.makeText(Login.this, "Please check your Internet Connection and Retry", Toast.LENGTH_LONG).show();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        loadingAnim.dismissDialog();

                        order_detail_adapter = new Order_detail_Adapter(Order_details.this,order_details);
                        recyclerView.setAdapter(order_detail_adapter);

                    }
                });
            }
        });
    }


    @Override
    public void onBackPressed() {super.onBackPressed(); }
}