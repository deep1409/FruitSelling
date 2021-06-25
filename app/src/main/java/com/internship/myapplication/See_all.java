package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.internship.myapplication.Adapter.AdapterHome;
import com.internship.myapplication.Adapter.AdapterHome1;
import com.internship.myapplication.Adapter.SeeAllAdapter;
import com.internship.myapplication.pojo.SeeAllPojo;
import com.internship.myapplication.pojo.pojoHome;
import com.internship.myapplication.pojo.pojoHome1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class See_all extends AppCompatActivity {

    ImageView back_arrow;
    RecyclerView rv;
    TextView itemTypeName;
    Context context;
    List<SeeAllPojo> seeAllPojoList;
    TextView no_items_tv;
    SeeAllAdapter adapter;
    Intent i;
    String s,fruit,veg,url,result,header;
    LoadingAnim loadingAnim;
    ExecutorService executorService1;
    EditText searchview;
    String search_url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        header = getString(R.string.header);
        context = getApplicationContext();
        fruit = "fruit";
        veg = "veg";

        itemTypeName = findViewById(R.id.see_all_item_type_name);
        rv = findViewById(R.id.see_all_recycleview);
        back_arrow = findViewById(R.id.see_all_back_arrow);
        searchview = findViewById(R.id.searchView);
        no_items_tv = findViewById(R.id.no_items_tv);


        loadingAnim = new LoadingAnim(See_all.this);
        executorService1 = Executors.newSingleThreadExecutor();
        i = getIntent();
        s = i.getStringExtra("type");
        itemTypeName.setText(i.getStringExtra("lable"));

        url = header + "home_items.php?item_type=" + s;
//        Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
        Log.d("test", "type: "+s);
        rv.setLayoutManager(new GridLayoutManager(See_all.this, 2));

        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search_url = header + "search_view_retrieve.php?item_name="+charSequence+"&item_type="+s;
                Log.d("TAG",""+search_url);
                retrieveSearchView();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        rv.setHasFixedSize(true);
        retrieveFromDB();

        seeAllPojoList = new ArrayList<>();
        adapter = new SeeAllAdapter(See_all.this,seeAllPojoList);
        rv.setAdapter(adapter);

//        seeAllPojoListFruit = new ArrayList<>();
//        seeAllPojoListFruit.add(new SeeAllPojo("Lemon","50","https://i.ndtvimg.com/mt/cooks/2014-11/lemon.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Guava","50","https://www.santosfood.com/wp-content/uploads/2020/01/4-4.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Custard apple","50","https://www.parasperfumers.com/upload/product_ecom/Custard-Apple-Seed-Oil.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Watermelon","50","https://cdn.britannica.com/99/143599-050-C3289491/Watermelon.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Grapes","50","https://www.aicr.org/wp-content/uploads/2020/01/shutterstock_533487490-640x462.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Apple","50","https://static.libertyprim.com/files/familles/pomme-large.jpg?1569271834"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Blackberry","50","https://4.imimg.com/data4/HR/HD/MY-2312690/blackberry-fruit-500x500.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Orange","50","https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Lemon","50","https://i.ndtvimg.com/mt/cooks/2014-11/lemon.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Guava","50","https://www.santosfood.com/wp-content/uploads/2020/01/4-4.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Custard apple","50","https://www.parasperfumers.com/upload/product_ecom/Custard-Apple-Seed-Oil.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Watermelon","50","https://cdn.britannica.com/99/143599-050-C3289491/Watermelon.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Grapes","50","https://www.aicr.org/wp-content/uploads/2020/01/shutterstock_533487490-640x462.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Apple","50","https://static.libertyprim.com/files/familles/pomme-large.jpg?1569271834"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Blackberry","50","https://4.imimg.com/data4/HR/HD/MY-2312690/blackberry-fruit-500x500.jpg"));
//        seeAllPojoListFruit.add(new SeeAllPojo("Orange","50","https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg"));

//        Log.d("test", "fruit list: "+seeAllPojoList.size());

//        seeAllPojoListVeg = new ArrayList<>();
//        seeAllPojoListVeg.add(new SeeAllPojo("Carrot","50","https://i.ndtvimg.com/mt/cooks/2014-11/carrots.jpg"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Cauliflower","50","https://specialtyproduce.com/sppics/112.png"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Cucumber","50","https://freshpoint.com/wp-content/uploads/2020/02/freshpoint-english-cucumber-scaled.jpg"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Brinjal","50","https://st1.thehealthsite.com/wp-content/uploads/2013/09/brinjal.jpg"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Potato","50","https://www.potatogoodness.com/wp-content/uploads/2019/02/white-potato-beauty-shot.png"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Tomato","50","https://upload.wikimedia.org/wikipedia/commons/8/89/Tomato_je.jpg"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Peas","50","https://cdn.shopify.com/s/files/1/1380/2059/products/Peas_grande.jpg?v=1598082087"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Green Chili","50","https://5.imimg.com/data5/EI/OA/HT/SELLER-109197867/red-onion-500x500.jpg"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Carrot","50","https://i.ndtvimg.com/mt/cooks/2014-11/carrots.jpg"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Cauliflower","50","https://specialtyproduce.com/sppics/112.png"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Cucumber","50","https://freshpoint.com/wp-content/uploads/2020/02/freshpoint-english-cucumber-scaled.jpg"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Brinjal","50","https://st1.thehealthsite.com/wp-content/uploads/2013/09/brinjal.jpg"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Potato","50","https://www.potatogoodness.com/wp-content/uploads/2019/02/white-potato-beauty-shot.png"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Tomato","50","https://upload.wikimedia.org/wikipedia/commons/8/89/Tomato_je.jpg"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Peas","50","https://cdn.shopify.com/s/files/1/1380/2059/products/Peas_grande.jpg?v=1598082087"));
//        seeAllPojoListVeg.add(new SeeAllPojo("Green Chili","50","https://5.imimg.com/data5/EI/OA/HT/SELLER-109197867/red-onion-500x500.jpg"));
//        Log.d("test", "veg list: "+seeAllPojoList.size());

//        if (itemTypeName.getText().toString().equals("Fruits")){
//            Log.d("test", "if block fruit: ");
//            adapter = new SeeAllAdapter(context,seeAllPojoListFruit);
//
//        }
//        else {
//            Log.d("test", "if block veg: ");
//            adapter = new SeeAllAdapter(context,seeAllPojoListVeg);
//        }
//        if (s == veg){
//            Log.d("test", "if block veg: ");
//            adapter = new SeeAllAdapter(context,seeAllPojoListVeg);
//
//        }

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    public void retrieveFromDB() {
        loadingAnim.startLoadingDialog();
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                try
                {
                    JsonParser o = new JsonParser();

                    result = o.insert(url);

                    seeAllPojoList = new ArrayList<>();

                    JSONObject jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("res");

                    Log.v("Login_DATA",""+result);

                    //for fruit
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                        SeeAllPojo p = new SeeAllPojo();

                        p.setId(jsonObject11.getString("id"));
                        p.setItem_name(jsonObject11.getString("item_name"));
                        p.setItem_price(jsonObject11.getString("item_price"));
                        p.setItem_url(jsonObject11.getString("item_img_url"));

                        seeAllPojoList.add(p);


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

                        adapter = new SeeAllAdapter(See_all.this,seeAllPojoList);
                        rv.setAdapter(adapter);

                    }
                });
            }
        });

    }


    public void retrieveSearchView() {
        //loadingAnim.startLoadingDialog();
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                try
                {
                    JsonParser o = new JsonParser();

                    result = o.insert(search_url);

                    seeAllPojoList = new ArrayList<>();

                    JSONObject jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("res");

                    Log.v("Login_DATA",""+result);

                    //for fruit
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                        SeeAllPojo p = new SeeAllPojo();

                        p.setId(jsonObject11.getString("id"));
                        p.setItem_name(jsonObject11.getString("item_name"));
                        p.setItem_price(jsonObject11.getString("item_price"));
                        p.setItem_url(jsonObject11.getString("item_img_url"));

                        seeAllPojoList.add(p);

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

                       // loadingAnim.dismissDialog();

                        if(seeAllPojoList.size() == 0){
                            no_items_tv.setVisibility(View.VISIBLE);
                            rv.setVisibility(View.INVISIBLE);
                        }else{
                            no_items_tv.setVisibility(View.INVISIBLE);
                            rv.setVisibility(View.VISIBLE);

                            adapter = new SeeAllAdapter(See_all.this,seeAllPojoList);
                            rv.setAdapter(adapter);
                        }
                    }
                });
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}