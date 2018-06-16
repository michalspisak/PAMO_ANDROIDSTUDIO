package com.example.michalspisak.aplikacja.ViewHolder;



import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michalspisak.aplikacja.Interface.ItemClickListener;
import com.example.michalspisak.aplikacja.R;


public class PrzedmiotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView przedmiot_name;
    public ImageView przedmiot_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public PrzedmiotViewHolder(View itemView) {
        super(itemView);

        przedmiot_name = (TextView)itemView.findViewById(R.id.przedmiot_name);
        przedmiot_image = (ImageView)itemView.findViewById(R.id.przedmiot_image);

        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(), false);


    }
}
