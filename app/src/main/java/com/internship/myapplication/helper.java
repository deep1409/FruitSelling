package com.internship.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.internship.myapplication.pojo.CartModel;

import java.util.ArrayList;

public class helper extends SQLiteOpenHelper {

    public static String dbname = "data";
    public static String tbname = "cart";

    //Table1 columns
    public static String id ="id";
    public static String item_id = "item_id";
    public static String item_name = "item_name";
    public static String item_price = "item_price";
    public static String item_quantity = "item_quantity";
    public static String item_img_url = "item_img_url";
//    public static String item_type = "item_type";


    //version code
    public static int dbversion = 1;

    static  String createquery = "create table "+tbname + "(" + id+ " integer primary key autoincrement," + item_id +" varchar(50)," + item_name +" varchar(50)," + item_price +" varchar(10)," + item_quantity +" varchar(50)," + item_img_url + " varchar(500));";


    public helper(Context context) {
        super(context, dbname, null, dbversion);
        Log.d("data","DB created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createquery);
        Log.d("data","tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //insert query for tb 1
    void insert(String item_id, String item_name,String item_price,String item_quantity,String item_img_url){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("item_id",item_id);
        cv.put("item_name",item_name);
        cv.put("item_price",item_price);
        cv.put("item_quantity",item_quantity);
        cv.put("item_img_url",item_img_url);
//        cv.put("item_type",item_type);
        db.insert(tbname,null,cv);

        //String q = "INSERT INTO " + tbname +"(a1,due_date,reg_date,cover_img,book) VALUES ('"+ author +"','" + due_date + "','"+ reg_date + "','" + cover_img + "','" + book + "')";
        //db.execSQL(q);
        Log.d("data","data inserted");
    }

    public ArrayList<CartModel> getDataFormliteDB(){
        ArrayList<CartModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "select * from "+ tbname ; //+" WHERE " + status2 + "=0";
        Cursor c = db.rawQuery(q,null);

        while (c.moveToNext())
        {
            String id = c.getString(0);
            String item_id = c.getString(1);
            String item_name = c.getString(2);
            String item_price = c.getString(3);
            String item_quantity = c.getString(4);
            String item_img_url = c.getString(5);
//            String item_type = c.getString(5);

            CartModel p = new CartModel();
            p.setId(id);
            p.setItem_id(item_id);
            p.setName(item_name);
            p.setPrice(item_price);
            p.setQuantity(item_quantity);
            p.setImage(item_img_url);
            list.add(p);
        }
        return  list;
    }

    //delete data from db...
    public void delete (String sid)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "delete from "+tbname+" where "+id+"="+sid;

        db.execSQL(sql);
        Log.d("data","deleted");
    }
}
