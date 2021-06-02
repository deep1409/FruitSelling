package com.internship.myapplication;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
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
    ImageView img;

    //RecycleView Initialization
    RecyclerView fruit_rv,veg_rv;
    List<pojoHome> fruit_list;
    List<pojoHome1> veg_list;

    AdapterHome adp1;
    AdapterHome1 adp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        fruit_rv = findViewById(R.id.fruit_recycle_view);
        veg_rv = findViewById(R.id.veg_recycle_view);

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

        fruit_rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        fruit_rv.setHasFixedSize(true);

        fruit_list = new ArrayList<>();
        fruit_list.add(new pojoHome("Test","Google","https://chromeunboxed.com/wp-content/uploads/2017/08/IDR_LOGIN_DEFAULT_USER_34@2x.png"));
        fruit_list.add(new pojoHome("Lemon","50","https://freepngimg.com/thumb/lemon/5-2-lemon-png-hd.png"));
        fruit_list.add(new pojoHome("Guava","50","https://lh3.googleusercontent.com/proxy/T3LITUEJdfW60ifSXqR3WOLzJgVMRtfXQxi1LsMdr22WgXFHAq5ZP98erXTNj4PIw03TH_NclqUYWD7gExa2uXULa_3JaSAWWNWUZ6C2aV57QsscMQHVfhbtVM01zB4"));
        fruit_list.add(new pojoHome("Custard apple","50","https://www.pngarts.com/files/3/Sugar-Apple-PNG-Download-Image.png"));
        fruit_list.add(new pojoHome("Watermalen","50","https://lh3.googleusercontent.com/proxy/Ri786hkch8YIJbsb94gEWX8E6Q7lbfLNjGXmTBjzgU7TtzrCk0quZdqGpz4lCnxFRsD1Iqrxe1fvFU4eFioMQiUJRTwV4c91oWZ3C4YaWZek6Pz8EQ3XzjK6-qWB4yUKWsFuk0iAYwXk"));
        fruit_list.add(new pojoHome("Grapes","50","https://lh3.googleusercontent.com/proxy/3FWwsdgBSq0a_hUTIapyZ4aCTzA1BCrZf3DhpFjx3hwQtTATtFzV96yXmGhbNt-YwcLAN0pBJvzt0YJqgcnvvQEfshqBJjakO2rSAzPFHX8Czt551YTjrq6EeQ5eXQ"));
        fruit_list.add(new pojoHome("Apple","50","https://freepngimg.com/thumb/apple/7-2-apple-fruit-png.png"));
        fruit_list.add(new pojoHome("Blackberry","50","https://freepngimg.com/thumb/blackberry/6-2-blackberry-fruit-free-png-image.png"));
        fruit_list.add(new pojoHome("Orange","50","https://pngimg.com/uploads/orange/orange_PNG780.png"));

        adp1 = new AdapterHome(Home.this,fruit_list);
        fruit_rv.setAdapter(adp1);

        veg_rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        veg_rv.setHasFixedSize(true);

        veg_list = new ArrayList<>();
        veg_list.add(new pojoHome1("Carrot","50","https://lh3.googleusercontent.com/proxy/Tzd0uXOSdAQ7GXgCiPml3RmFkm7SxPeJxOOPcLOxa2Jn-xmocOAPZZKJvH0IFEqWiYfHxk5lpTfKqDhbLeJp9qNTCdv2GaMd"));
        veg_list.add(new pojoHome1("Cauliflower","50"," https://pngimg.com/uploads/cauliflower/cauliflower_PNG12668.png"));
        veg_list.add(new pojoHome1("Cucumber","50","  https://freepngimg.com/thumb/cucumber/8-2-cucumber-png-pic.png"));
        veg_list.add(new pojoHome1("Brinjal","50"," https://lh3.googleusercontent.com/proxy/dS-1z_s1lXfnRqjJ2mt9F5-SKhhgm-DPUKg4QjIAmgDXoRABWEgwqKvQ1GYv-IQ8B76z4fkgTGAm-t_HtjBi8FUZnVUKMfLaBhmTFMkjTAVby3jhP0v7OlfEqsHB5kkG7qn0LYbseQ"));
        veg_list.add(new pojoHome1("Potato","50"," https://pngimg.com/uploads/potato/potato_png2391.png"));
        veg_list.add(new pojoHome1("Tomato","50"," https://purepng.com/public/uploads/large/purepng.com-red-tomatoestomatosalad-fruitred-fruittomatoes-1701527316509mirzn.png"));
        veg_list.add(new pojoHome1("Peas","50"," https://i.pinimg.com/736x/9e/1f/5b/9e1f5b5a9d1d92191e410cc9a734ff50.jpg"));
        veg_list.add(new pojoHome1("Green Chili","50"," https://pngimg.com/uploads/cauliflower/cauliflower_PNG12668.png"));

        adp2 = new AdapterHome1(Home.this,veg_list);
        veg_rv.setAdapter(adp2);

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
