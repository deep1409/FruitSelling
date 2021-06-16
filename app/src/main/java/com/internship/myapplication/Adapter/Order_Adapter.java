package com.internship.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.internship.myapplication.Order_details;
import com.internship.myapplication.R;
import com.internship.myapplication.pojo.OrderModel;
import com.internship.myapplication.pojo.OrderModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.MyViewHolder> {

    List<OrderModel> list;
    Context context;

    public Order_Adapter(Context context, List<OrderModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Order_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_order,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Order_Adapter.MyViewHolder holder, int position) {

        OrderModel p = list.get(position);
//        Picasso.get().load(p.getImage()).into(holder.list_img_1);
          holder.order_id.setText(p.getOrderid().toString());
//        holder.quantity_1.setText(p.getQuantity().toString());
        holder.status.setText(p.getStatus().toString());
        holder.date.setText(p.getDate().toString());
        holder.list_order_price.setText("₹"+p.getPrice().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context.getApplicationContext(), Order_details.class);
                intent.putExtra("order_id",p.getOrderid());
                context.startActivity(intent);
                Toast.makeText(context.getApplicationContext(), ""+p.getOrderid(), Toast.LENGTH_SHORT).show();
            }
        });
//        int total = Integer.parseInt(p.getPrice())*Integer.parseInt(p.getQuantity());

//        holder.list_order_price.setText(""+total);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView list_img_1;
        TextView list_order_name,list_order_price,quantity_1,status,date,order_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            list_img_1 = itemView.findViewById(R.id.list_img_1);
//            list_order_name = itemView.findViewById(R.id.list_order_name);
            list_order_price = itemView.findViewById(R.id.list_order_price);
//            quantity_1 = itemView.findViewById(R.id.quantity_1);
            status = itemView.findViewById(R.id.status);
            date = itemView.findViewById(R.id.date);
            order_id = itemView.findViewById(R.id.order_id);

        }
    }
}
