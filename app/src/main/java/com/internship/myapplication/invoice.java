package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.internship.myapplication.Adapter.Cart_Adapter;
import com.internship.myapplication.pojo.CartModel;
import com.internship.myapplication.pojo.Profile_Pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class invoice extends AppCompatActivity {
    ImageView back_btn_invoice;
    TextView date1,total_price,address;
    Button Place_order_button;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    Cart_Adapter adapter;
    RecyclerView recyclerView;
    helper helper;
    List<CartModel> cart_item;
    Intent i ;
    SharedPreferences preferences;
    ExecutorService service;
    LoadingAnim loading;
    String result,URL,header,shared_email_id,customer_id,customer_address;
    SharedPreferences sp;
    String orderMasterUrl,total_price_intent,order_master_id;
    String date_tv,orderDetailsURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        back_btn_invoice = findViewById(R.id.back_btn_invoice);
        Place_order_button= findViewById(R.id.Place_order_button);
        date1 = findViewById(R.id.date);
        recyclerView = findViewById(R.id.recyclerView);
        total_price = findViewById(R.id.total_price);
        address = findViewById(R.id.textView15);

        sp = PreferenceManager.getDefaultSharedPreferences(invoice.this);
        shared_email_id = sp.getString("email_id","");
        header = getString(R.string.header);
        URL = header + "user_login.php?customer_email=" +shared_email_id;
        orderMasterUrl = header + "insert_order_master.php";
        orderDetailsURL = header + "insert_order_details.php";
        Log.d("invoice", "URL: "+orderMasterUrl+"\ndetails: "+orderDetailsURL);


        helper = new helper(invoice.this);
        service = Executors.newSingleThreadExecutor();
        loading = new LoadingAnim(invoice.this);
        getData();

        date_tv = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date1.setText(date_tv);

        preferences = PreferenceManager.getDefaultSharedPreferences(invoice.this);
        address.setText(preferences.getString("customer_address",""));

        i = getIntent();
        total_price_intent = i.getStringExtra("total_price");
        total_price.setText(total_price_intent);

        Place_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
                loading.dismissDialog();
                Intent intent =new Intent(invoice.this,Orders.class);
                startActivity(intent);
                Toast.makeText(invoice.this, "Thank you for your Purchase", Toast.LENGTH_SHORT).show();
            }
        });

        back_btn_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cart_item = helper.getDataFormliteDB();

        adapter = new Cart_Adapter(invoice.this,cart_item,0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        for (CartModel e: cart_item){
            Log.d("fore", "item_id: "+cart_item.get(cart_item.indexOf(e)).getItem_id()+"\nitem_name: "+cart_item.get(cart_item.indexOf(e)).getName()+"" +
                    "\nitem_quantity: "+cart_item.get(cart_item.indexOf(e)).getQuantity()+"" +
                    "\nitem_price: "+cart_item.get(cart_item.indexOf(e)).getPrice());
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getData(){
        loading.startLoadingDialog();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    JsonParser js = new JsonParser();
                    result = js.insert(URL);
                    Log.d("invoice", "result: "+result);
                    JSONObject jo = new JSONObject(result);
                    JSONArray ja = jo.getJSONArray("res");
                    for (int i = 0; i <ja.length() ; i++) {

                        JSONObject  jo1 = ja.getJSONObject(i);

                        customer_address = jo1.getString("customer_address");
                        customer_id = jo1.getString("customer_id");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismissDialog();
                        Toast.makeText(invoice.this, ""+customer_address, Toast.LENGTH_SHORT).show();
                        address.setText(customer_address);
                    }
                });

            }
        });

    }

    public  void postData(){
        loading.startLoadingDialog();
        AndroidNetworking.post(orderMasterUrl)
                .addBodyParameter("order_date",date_tv)
                .addBodyParameter("total_price",total_price_intent.substring(1))
                .addBodyParameter("order_status","Undelivered")
                .addBodyParameter("customer_id",customer_id)
                .setTag("Test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        order_master_id = response;
                        postOrderDetails();
                    }

                    @Override
                    public void onError(ANError anError) {
                       loading.dismissDialog();
                        Toast.makeText(invoice.this, ""+anError, Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void postOrderDetails(){
        for(CartModel e: cart_item){
            AndroidNetworking.post(orderDetailsURL)
                    .addBodyParameter("order_id",order_master_id)
                    .addBodyParameter("item_id",cart_item.get(cart_item.indexOf(e)).getItem_id())
                    .addBodyParameter("item_quantity",cart_item.get(cart_item.indexOf(e)).getQuantity())
                    .addBodyParameter("item_prize",cart_item.get(cart_item.indexOf(e)).getPrice())
                    .setTag("Test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {

                            helper.delete(cart_item.get(cart_item.indexOf(e)).getId());

                        }

                        @Override
                        public void onError(ANError anError) {
                            loading.dismissDialog();
                            Toast.makeText(invoice.this, ""+anError, Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }
}
