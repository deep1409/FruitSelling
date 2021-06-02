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

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


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


      /*  screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        Drawer_Adapter adapter = new Drawer_Adapter(Arrays.asList(
                //createItemFor(POS_CLOSE),
                //createItemFor(POS_DASHBOARD).setChecked(true),
                //createItemFor(POS_MY_PROFILE),
                //createItemFor(POS_MY_CART),
                //createItemFor(POS_ABOUT_US),
                //new SpaceItem(260),
               // createItemFor(POS_LOGOUT)
        ));
        adapter.setListener(this);

        //drawer_list
        RecyclerView list =findViewById(R.id.drop);
        list.setNestedScrollingEnabled(false);
        //linear layout
        list.setLayoutManager(new LinearLayoutManager(this));

        adapter.setSelected(POS_DASHBOARD);

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private DrawerItem createItemFor(int postion){
        return new SimpleItem(screenIcons[postion],screenTitles[postion])
                .withIconTint(color(R.color.light_blue));
                //.withTextTint(color(R.color.black));
                //.withSelectedIconTint(color(R.color.light_blue));
                //.withSelectedItemTextTint(color(R.color.light_blue));

    }

    @ColorInt
    private  int color(@ColorRes int res){
        return ContextCompat.getColor(this,res);
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for(int i = 0; i<ta.length();i++)
        {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[id] = ContextCompat.getDrawable(this,id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

   // slidingRootNav.closeMenu();
   // transaction.addToBackStack(null);
   // transaction.commit();

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(position == POS_DASHBOARD){
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container,dashBoardFragment);
        }
    }
    }*/

    }
}
