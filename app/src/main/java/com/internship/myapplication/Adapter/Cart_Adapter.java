package com.internship.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.internship.myapplication.R;
import com.internship.myapplication.pojo.CartModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder> {

    List<CartModel> list;
    Context context;

    public Cart_Adapter(Context context, List<CartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Cart_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_Adapter.MyViewHolder holder, int position) {

        CartModel p = list.get(position);
        Picasso.get().load(p.getImage()).into(holder.list_img);
        holder.list_cart_name.setText(p.getName().toString());
        holder.quantity.setText(p.getQuantity().toString());

        int total = Integer.parseInt(p.getPrice())*Integer.parseInt(p.getQuantity());

        holder.list_cart_price.setText(""+total);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView list_img;
        TextView list_cart_name,list_cart_price,quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            list_img = itemView.findViewById(R.id.list_img);
            list_cart_name = itemView.findViewById(R.id.list_cart_name);
            list_cart_price = itemView.findViewById(R.id.list_cart_price);
            quantity = itemView.findViewById(R.id.quantity);

        }
    }
}