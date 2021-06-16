package com.internship.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.internship.myapplication.Order_details;
import com.internship.myapplication.R;
import com.internship.myapplication.pojo.OrderModel;
import com.internship.myapplication.pojo.Order_detail_pojo;

import java.util.List;

public class Order_detail_Adapter extends RecyclerView.Adapter<Order_detail_Adapter.MyViewholder>  {

    List<Order_detail_pojo> list;
    Context context;

    public Order_detail_Adapter(Context context, List<Order_detail_pojo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_details_list,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        Order_detail_pojo p = list.get(position);
        holder.item_id.setText(p.getItem_id());
        holder.item_price.setText(p.getItem_prize());
        holder.item_quantity.setText(p.getItem_quantity());
        Log.d("item_id", "Item_id: "+p.getItem_id()+"\n item_price: "+p.getItem_prize()+"\n item_quantity: "+p.getItem_quantity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView item_id,item_quantity,item_price;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            item_id = itemView.findViewById(R.id.item_id);
            item_price = itemView.findViewById(R.id.item_price1);
            item_quantity = itemView.findViewById(R.id.item_quantity);

        }
    }
}
