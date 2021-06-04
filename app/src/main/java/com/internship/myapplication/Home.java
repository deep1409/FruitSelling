package com.internship.myapplication;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.internship.myapplication.Adapter.AdapterHome;
import com.internship.myapplication.Adapter.AdapterHome1;
import com.internship.myapplication.pojo.pojoHome;
import com.internship.myapplication.pojo.pojoHome1;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Home extends AppCompatActivity /*implements Drawer_Adapter.OnItemSelectedListener */{

    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_MY_PROFILE = 2;
    private static final int POS_MY_CART = 3;
    private static final int POS_ABOUT_US = 4;
    private static final int POS_LOGOUT = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    public SlidingRootNav slidingRootNav;
    ImageView img,imageView;

    //RecycleView Initialization
    RecyclerView fruit_rv,veg_rv;
    List<pojoHome> fruit_list;
    List<pojoHome1> veg_list;

    AdapterHome adp1;
    AdapterHome1 adp2;
    TextView fruitSeeAll,vegSeeAll;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        fruitSeeAll = findViewById(R.id.fruit_see_all);
        vegSeeAll = findViewById(R.id.veg_see_all);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        imageView = findViewById(R.id.imageView);
        fruit_rv = findViewById(R.id.fruit_recycle_view);
        veg_rv = findViewById(R.id.veg_recycle_view);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Home.this,Cart.class);
                startActivity(intent);
            }
        });

        slidingRootNav = (SlidingRootNav) new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withMenuLayout(R.layout.navigation_drawer)
                .inject();
        //.withDragDistance(180)
        //.withRootViewScale(0.75f)
        //.withRootViewElevation(25)
        //.withContentClickableWhenMenuOpened(false)
        //.withSavedState(savedInstanceState)

        fruit_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        fruit_rv.setHasFixedSize(true);

        fruit_list = new ArrayList<>();
        fruit_list.add(new pojoHome("Lemon","50","https://i.ndtvimg.com/mt/cooks/2014-11/lemon.jpg"));
        fruit_list.add(new pojoHome("Guava","50","https://www.santosfood.com/wp-content/uploads/2020/01/4-4.jpg"));
        fruit_list.add(new pojoHome("Custard apple","50","https://www.parasperfumers.com/upload/product_ecom/Custard-Apple-Seed-Oil.jpg"));
        fruit_list.add(new pojoHome("Watermelon","50","https://cdn.britannica.com/99/143599-050-C3289491/Watermelon.jpg"));
        fruit_list.add(new pojoHome("Grapes","50","https://www.aicr.org/wp-content/uploads/2020/01/shutterstock_533487490-640x462.jpg"));
        fruit_list.add(new pojoHome("Apple","50","https://static.libertyprim.com/files/familles/pomme-large.jpg?1569271834"));
        fruit_list.add(new pojoHome("Blackberry","50","https://4.imimg.com/data4/HR/HD/MY-2312690/blackberry-fruit-500x500.jpg"));
        fruit_list.add(new pojoHome("Orange","50","https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg"));


        adp1 = new AdapterHome(Home.this,fruit_list);
        fruit_rv.setAdapter(adp1);

        veg_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        veg_rv.setHasFixedSize(true);

        veg_list = new ArrayList<>();
        veg_list.add(new pojoHome1("Carrot","50","https://i.ndtvimg.com/mt/cooks/2014-11/carrots.jpg"));
        veg_list.add(new pojoHome1("Cauliflower","50","https://specialtyproduce.com/sppics/112.png"));
        veg_list.add(new pojoHome1("Cucumber","50","https://freshpoint.com/wp-content/uploads/2020/02/freshpoint-english-cucumber-scaled.jpg"));
        veg_list.add(new pojoHome1("Brinjal","50","https://st1.thehealthsite.com/wp-content/uploads/2013/09/brinjal.jpg"));
        veg_list.add(new pojoHome1("Potato","50","https://www.potatogoodness.com/wp-content/uploads/2019/02/white-potato-beauty-shot.png"));
        veg_list.add(new pojoHome1("Tomato","50","https://upload.wikimedia.org/wikipedia/commons/8/89/Tomato_je.jpg"));
        veg_list.add(new pojoHome1("Peas","50","https://cdn.shopify.com/s/files/1/1380/2059/products/Peas_grande.jpg?v=1598082087"));
        veg_list.add(new pojoHome1("Green Chili","50","https://5.imimg.com/data5/EI/OA/HT/SELLER-109197867/red-onion-500x500.jpg"));


        adp2 = new AdapterHome1(Home.this,veg_list);
        veg_rv.setAdapter(adp2);

        fruitSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,See_all.class);
                i.putExtra("type","fruit");
                i.putExtra("lable","Fruits");
                startActivity(i);
            }
        });
        vegSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,See_all.class);
                i.putExtra("type","veg");
                i.putExtra("lable","Vegetables");
                startActivity(i);
            }
        });


//        screenIcons = loadScreenIcons();
//        screenTitles = loadScreenTitles();
//
//        Drawer_Adapter adapter = new Drawer_Adapter(Arrays.asList(
//                //createItemFor(POS_CLOSE),
//                //createItemFor(POS_DASHBOARD).setChecked(true),
//                //createItemFor(POS_MY_PROFILE),
//                //createItemFor(POS_MY_CART),
//                //createItemFor(POS_ABOUT_US),
//                //new SpaceItem(260),
//               // createItemFor(POS_LOGOUT)
//        ));
//        adapter.setListener(this);
//
//        //drawer_list
//        RecyclerView list =findViewById(R.id.drop);
//        list.setNestedScrollingEnabled(false);
//        //linear layout
//        list.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter.setSelected(POS_DASHBOARD);
//
//    }
//
//    private void setSupportActionBar(Toolbar toolbar) {
//    }
//
//    private DrawerItem createItemFor(int postion){
//        return new SimpleItem(screenIcons[postion],screenTitles[postion])
//                .withIconTint(color(R.color.light_blue));
//                //.withTextTint(color(R.color.black));
//                //.withSelectedIconTint(color(R.color.light_blue));
//                //.withSelectedItemTextTint(color(R.color.light_blue));
//
//    }
//
//    @ColorInt
//    private  int color(@ColorRes int res){
//        return ContextCompat.getColor(this,res);
//    }
//
//    private String[] loadScreenTitles() {
//        return getResources().getStringArray(R.array.id_activityScreenTitles);
//    }
//
//    private Drawable[] loadScreenIcons() {
//        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcons);
//        Drawable[] icons = new Drawable[ta.length()];
//        for(int i = 0; i<ta.length();i++)
//        {
//            int id = ta.getResourceId(i, 0);
//            if (id != 0) {
//                icons[id] = ContextCompat.getDrawable(this,id);
//            }
//        }
//        ta.recycle();
//        return icons;
//    }
//
//    @Override
//    public void onBackPressed() {
//        finish();
//    }
//
//   // slidingRootNav.closeMenu();
//   // transaction.addToBackStack(null);
//   // transaction.commit();
//
//    @Override
//    public void onItemSelected(int position) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        if(position == POS_DASHBOARD){
//            DashBoardFragment dashBoardFragment = new DashBoardFragment();
//            transaction.replace(R.id.container,dashBoardFragment);
//        }
//    }
//    }

    }
}
