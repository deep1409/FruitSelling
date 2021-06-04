package com.internship.myapplication;

import android.view.ViewGroup;

public abstract class DrawerItem <T extends Drawer_Adapter.ViewHolder>{
    protected boolean isChecked;
    public abstract T createViewHolder(ViewGroup parent);

    public abstract void bindViewHolder(T holder);

    public DrawerItem<T>setChecked(boolean isChecked){
        this.isChecked = isChecked;
        return this;
    }

    public boolean isChecked(){
        if(isChecked){
            return true;
        }
        else{
            return false;
        }
       // return isChecked();
    }

    public boolean isSelectable(){
        return true;
    }
}
