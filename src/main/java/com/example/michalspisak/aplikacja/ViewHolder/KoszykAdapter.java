package com.example.michalspisak.aplikacja.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.michalspisak.aplikacja.Interface.ItemClickListener;
import com.example.michalspisak.aplikacja.Koszyk;
import com.example.michalspisak.aplikacja.Model.Zamowienie;
import com.example.michalspisak.aplikacja.R;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class KoszykViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_cart_name, txt_price;
    public ImageView img_cart_count;

    private ItemClickListener itemClickListener;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }



    public KoszykViewHolder(View itemView) {
        super(itemView);

        txt_cart_name = (TextView)itemView.findViewById(R.id.cart_item_name);
        txt_price = (TextView)itemView.findViewById(R.id.cart_item_Price);
        img_cart_count = (ImageView)itemView.findViewById(R.id.cart_item_count);

    }

    @Override
    public void onClick(View view) {

    }
}

public class KoszykAdapter extends RecyclerView.Adapter<KoszykViewHolder> {

    private List<Zamowienie> listData = new ArrayList<>();
    private Context context;

    public KoszykAdapter(List<Zamowienie> listData, Context context){
        this.listData = listData;
        this.context = context;
    }


    @Override
    public KoszykViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=    LayoutInflater.from(context);
        View    itemView = inflater.inflate(R.layout.koszyk_layout,parent,false);
        return new KoszykViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KoszykViewHolder holder, int position) {

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+listData.get(position).getIlosc(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int cena = (Integer.parseInt(listData.get(position).getCena()))*(Integer.parseInt(listData.get(position).getIlosc()));
        holder.txt_price.setText(fmt.format(cena));
        holder.txt_cart_name.setText(listData.get(position).getProduktNazwa());


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
