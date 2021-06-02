package com.internship.myapplication;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.security.PrivateKey;
import java.security.PublicKey;

public class SimpleItem extends DrawerItem<SimpleItem.ViewHolder> {

    private int selectedItemIconint;
    private int selectedItemTextint;

    private int normalItemIconTint ;
    private int normalItemTextTint ;

    private Drawable icon;
    private String title;

    public SimpleItem(Drawable icon, String title){
        this.icon = icon;
        this.title = title;

    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_optioin,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.title.setText(title);
        holder.icon.setImageDrawable(icon);

        holder.title.setText(isChecked ? selectedItemTextint : normalItemTextTint);
        holder.title.setText(isChecked ? selectedItemIconint : normalItemIconTint);
    }

    public SimpleItem withSelectedIconTint(int SelectedItemIconTint){
        this.selectedItemIconint = selectedItemIconint;
        return this;
    }

    public SimpleItem withSelectedTextTint(int SelectedItemTextTint){
        this.selectedItemTextint = selectedItemTextint;
        return this;
    }
    public SimpleItem withIconTint(int normalItemIconTint){
        this.normalItemIconTint = normalItemIconTint;
        return this;
    }
    public SimpleItem withTextTint(int normalItemTextTint){
        this.normalItemTextTint = normalItemTextTint;
        return this;
    }



    static class ViewHolder extends Drawer_Adapter.ViewHolder{

        private ImageView icon;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);

        }
    }
}
