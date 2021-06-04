package com.internship.myapplication.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static abstract class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private DrawerAdapter drawerAdapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
