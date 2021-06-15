package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.internship.myapplication.Adapter.Cart_Adapter;
import com.internship.myapplication.pojo.CartModel;
import com.internship.myapplication.pojo.Profile_Pojo;

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

        helper = new helper(invoice.this);

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date1.setText(date);

        preferences = PreferenceManager.getDefaultSharedPreferences(invoice.this);
        address.setText(preferences.getString("customer_address",""));

        i = getIntent();
        total_price.setText(i.getStringExtra("total_price"));

        Place_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
