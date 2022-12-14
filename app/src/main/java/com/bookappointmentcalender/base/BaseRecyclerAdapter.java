package com.bookappointmentcalender.base;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public abstract class BaseRecyclerAdapter<E, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<E> items;

    public BaseRecyclerAdapter(List<E> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<E> getItems(){
        return items;
    }

    public E getItemAt(int position){
        return items.get(position);

    }

}
