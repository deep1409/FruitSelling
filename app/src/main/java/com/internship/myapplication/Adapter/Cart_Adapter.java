package com.internship.myapplication.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.internship.myapplication.R;
import com.internship.myapplication.helper;
import com.internship.myapplication.pojo.CartModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder> {

    List<CartModel> list;
    Context context;
    helper helper;
    Integer cart_or_place_order;


    public Cart_Adapter(Context context, List<CartModel> list,Integer cart_or_place_order) {
        this.context = context;
        this.list = list;
        this.cart_or_place_order = cart_or_place_order;
    }

    @NonNull
    @Override
    public Cart_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_Adapter.MyViewHolder holder, int position) {

        helper = new helper(context.getApplicationContext());

        CartModel p = list.get(position);
        Picasso.get().load(p.getImage()).into(holder.list_img);
        //
        holder.list_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ab = new AlertDialog.Builder(view.getContext());
                ab.setCancelable(false);
                ab.setTitle("Warning!!");
                ab.setMessage("Are you sure you want to delete this item?");
                ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        helper.delete(p.getId());
//                        Toast.makeText(context, ""+p.getId(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (context.getApplicationContext(),context.getClass()).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = ab.create();
                alertDialog.show();

//              Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
        holder.list_cart_name.setText(p.getName().toString());
        holder.quantity.setText(p.getQuantity().toString());

        int total = Integer.parseInt(p.getPrice())*Integer.parseInt(p.getQuantity());

        holder.list_cart_price.setText("₹" + total);

        if(cart_or_place_order == 0){
            holder.list_cancel.setVisibility(View.GONE);
        }else{
            holder.list_cancel.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView list_img,list_cancel;
        TextView list_cart_name,list_cart_price,quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            list_img = itemView.findViewById(R.id.list_img);
            list_cancel = itemView.findViewById(R.id.cancel);
            list_cart_name = itemView.findViewById(R.id.list_cart_name);
            list_cart_price = itemView.findViewById(R.id.list_cart_price);
            quantity = itemView.findViewById(R.id.quantity);



        }
    }
}
