package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.internship.myapplication.Adapter.Cart_Adapter;
import com.internship.myapplication.pojo.CartModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class invoice extends AppCompatActivity {
    ImageView back_btn_invoice;
    TextView date1;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    Cart_Adapter adapter;
    RecyclerView recyclerView;
    helper helper;
    List<CartModel> cart_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        back_btn_invoice = findViewById(R.id.back_btn_invoice);
        date1 = findViewById(R.id.date);
        recyclerView = findViewById(R.id.recyclerView);

        helper = new helper(invoice.this);

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date1.setText(date);

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
