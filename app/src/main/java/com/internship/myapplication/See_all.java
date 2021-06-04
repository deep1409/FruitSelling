package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.internship.myapplication.Adapter.SeeAllAdapter;
import com.internship.myapplication.pojo.SeeAllPojo;
import com.internship.myapplication.pojo.pojoHome;
import com.internship.myapplication.pojo.pojoHome1;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class See_all extends AppCompatActivity {

    ImageView back_arrow;
    RecyclerView rv;
    TextView itemTypeName;
    Context context;
    List<SeeAllPojo> seeAllPojoListFruit,seeAllPojoListVeg;
    SeeAllAdapter adapter;
    Intent i;
    String s,fruit,veg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        context = getApplicationContext();
        fruit = "fruit";
        veg = "veg";

        itemTypeName = findViewById(R.id.see_all_item_type_name);
        rv = findViewById(R.id.see_all_recycleview);
        back_arrow = findViewById(R.id.see_all_back_arrow);

        i = getIntent();
        s = i.getStringExtra("type");
        itemTypeName.setText(i.getStringExtra("lable"));

        Log.d("test", "type: "+s);
        rv.setLayoutManager(new GridLayoutManager(See_all.this, 2));
        rv.setHasFixedSize(true);

        seeAllPojoListFruit = new ArrayList<>();
        seeAllPojoListFruit.add(new SeeAllPojo("Lemon","50","https://i.ndtvimg.com/mt/cooks/2014-11/lemon.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Guava","50","https://www.santosfood.com/wp-content/uploads/2020/01/4-4.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Custard apple","50","https://www.parasperfumers.com/upload/product_ecom/Custard-Apple-Seed-Oil.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Watermelon","50","https://cdn.britannica.com/99/143599-050-C3289491/Watermelon.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Grapes","50","https://www.aicr.org/wp-content/uploads/2020/01/shutterstock_533487490-640x462.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Apple","50","https://static.libertyprim.com/files/familles/pomme-large.jpg?1569271834"));
        seeAllPojoListFruit.add(new SeeAllPojo("Blackberry","50","https://4.imimg.com/data4/HR/HD/MY-2312690/blackberry-fruit-500x500.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Orange","50","https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Lemon","50","https://i.ndtvimg.com/mt/cooks/2014-11/lemon.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Guava","50","https://www.santosfood.com/wp-content/uploads/2020/01/4-4.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Custard apple","50","https://www.parasperfumers.com/upload/product_ecom/Custard-Apple-Seed-Oil.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Watermelon","50","https://cdn.britannica.com/99/143599-050-C3289491/Watermelon.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Grapes","50","https://www.aicr.org/wp-content/uploads/2020/01/shutterstock_533487490-640x462.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Apple","50","https://static.libertyprim.com/files/familles/pomme-large.jpg?1569271834"));
        seeAllPojoListFruit.add(new SeeAllPojo("Blackberry","50","https://4.imimg.com/data4/HR/HD/MY-2312690/blackberry-fruit-500x500.jpg"));
        seeAllPojoListFruit.add(new SeeAllPojo("Orange","50","https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg"));

        Log.d("test", "fruit list: "+seeAllPojoListFruit.size());

        seeAllPojoListVeg = new ArrayList<>();
        seeAllPojoListVeg.add(new SeeAllPojo("Carrot","50","https://i.ndtvimg.com/mt/cooks/2014-11/carrots.jpg"));
        seeAllPojoListVeg.add(new SeeAllPojo("Cauliflower","50","https://specialtyproduce.com/sppics/112.png"));
        seeAllPojoListVeg.add(new SeeAllPojo("Cucumber","50","https://freshpoint.com/wp-content/uploads/2020/02/freshpoint-english-cucumber-scaled.jpg"));
        seeAllPojoListVeg.add(new SeeAllPojo("Brinjal","50","https://st1.thehealthsite.com/wp-content/uploads/2013/09/brinjal.jpg"));
        seeAllPojoListVeg.add(new SeeAllPojo("Potato","50","https://www.potatogoodness.com/wp-content/uploads/2019/02/white-potato-beauty-shot.png"));
        seeAllPojoListVeg.add(new SeeAllPojo("Tomato","50","https://upload.wikimedia.org/wikipedia/commons/8/89/Tomato_je.jpg"));
        seeAllPojoListVeg.add(new SeeAllPojo("Peas","50","https://cdn.shopify.com/s/files/1/1380/2059/products/Peas_grande.jpg?v=1598082087"));
        seeAllPojoListVeg.add(new SeeAllPojo("Green Chili","50","https://5.imimg.com/data5/EI/OA/HT/SELLER-109197867/red-onion-500x500.jpg"));
        seeAllPojoListVeg.add(new SeeAllPojo("Carrot","50","https://i.ndtvimg.com/mt/cooks/2014-11/carrots.jpg"));
        seeAllPojoListVeg.add(new SeeAllPojo("Cauliflower","50","https://specialtyproduce.com/sppics/112.png"));
        seeAllPojoListVeg.add(new SeeAllPojo("Cucumber","50","https://freshpoint.com/wp-content/uploads/2020/02/freshpoint-english-cucumber-scaled.jpg"));
        seeAllPojoListVeg.add(new SeeAllPojo("Brinjal","50","https://st1.thehealthsite.com/wp-content/uploads/2013/09/brinjal.jpg"));
        seeAllPojoListVeg.add(new SeeAllPojo("Potato","50","https://www.potatogoodness.com/wp-content/uploads/2019/02/white-potato-beauty-shot.png"));
        seeAllPojoListVeg.add(new SeeAllPojo("Tomato","50","https://upload.wikimedia.org/wikipedia/commons/8/89/Tomato_je.jpg"));
        seeAllPojoListVeg.add(new SeeAllPojo("Peas","50","https://cdn.shopify.com/s/files/1/1380/2059/products/Peas_grande.jpg?v=1598082087"));
        seeAllPojoListVeg.add(new SeeAllPojo("Green Chili","50","https://5.imimg.com/data5/EI/OA/HT/SELLER-109197867/red-onion-500x500.jpg"));
        Log.d("test", "veg list: "+seeAllPojoListVeg.size());

        if (itemTypeName.getText().toString().equals("Fruits")){
            Log.d("test", "if block fruit: ");
            adapter = new SeeAllAdapter(context,seeAllPojoListFruit);

        }
        else {
            Log.d("test", "if block veg: ");
            adapter = new SeeAllAdapter(context,seeAllPojoListVeg);
        }
//        if (s == veg){
//            Log.d("test", "if block veg: ");
//            adapter = new SeeAllAdapter(context,seeAllPojoListVeg);
//
//        }


        rv.setAdapter(adapter);


    }
}