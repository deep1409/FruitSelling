package com.internship.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.internship.myapplication.Item_description;
import com.internship.myapplication.R;
import com.internship.myapplication.pojo.SeeAllPojo;
import com.internship.myapplication.pojo.pojoHome;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeeAllAdapter extends RecyclerView.Adapter<SeeAllAdapter.myViewHolder> {

    Context context;
    List<SeeAllPojo> list;

    public SeeAllAdapter(Context context, List<SeeAllPojo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_custom_cardview,parent, false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        Log.d("test", "in adapter class bindview: ");
        SeeAllPojo p = list.get(position);
        holder.itemName.setText(p.getItem_name());
        holder.itemPrice.setText(p.getItem_price()+" ₹/kg");
        Picasso.get().load(p.getItem_url()).into(holder.item_img);

        Log.d("test", "itemName: "+p.getItem_name());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getApplicationContext(), Item_description.class);
                i.putExtra("img",p.getItem_url());
                i.putExtra("name",p.getItem_name());
                i.putExtra("price",p.getItem_price());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView item_img;
        TextView itemName,itemPrice;
        MaterialCardView cardView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img = (ImageView) itemView.findViewById(R.id.item_img);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemPrice = (TextView) itemView.findViewById(R.id.item_price);
            cardView = (MaterialCardView) itemView.findViewById(R.id.home_cardview);
        }
    }
}
