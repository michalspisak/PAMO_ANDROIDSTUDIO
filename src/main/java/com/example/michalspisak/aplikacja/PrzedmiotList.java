package com.example.michalspisak.aplikacja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowId;
import android.widget.Toast;

import com.example.michalspisak.aplikacja.Interface.ItemClickListener;
import com.example.michalspisak.aplikacja.Model.Przedmiot;
import com.example.michalspisak.aplikacja.ViewHolder.PrzedmiotViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PrzedmiotList extends AppCompatActivity {

    RecyclerView    recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference przedmiotList;

    String  katetegoriaId="";

    FirebaseRecyclerAdapter<Przedmiot, PrzedmiotViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przedmiot_list);

        //baza wczytywanie

        database = FirebaseDatabase.getInstance();
        przedmiotList = database.getReference("Przedmiot");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_przedmiot);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // tutaj jest nasz Intent

        if(getIntent() != null)
            katetegoriaId = getIntent().getStringExtra("KategoriaId");

        if(!katetegoriaId.isEmpty() && katetegoriaId != null)
        {
            loadListPrzedmiot(katetegoriaId);
        }

    }

    private void loadListPrzedmiot(String katetegoriaId) {
        adapter = new FirebaseRecyclerAdapter<Przedmiot, PrzedmiotViewHolder>(Przedmiot.class, R.layout.przedmiot_item, PrzedmiotViewHolder.class,przedmiotList.orderByChild("MenuId").equalTo(katetegoriaId)) {
            @Override
            protected void populateViewHolder(PrzedmiotViewHolder viewHolder, Przedmiot model, int position) {
                viewHolder.przedmiot_name.setText(model.getNazwa());
                Picasso.with(getBaseContext()).load(model.getZdjecie())
                        .into(viewHolder.przedmiot_image);

                final Przedmiot local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent  przedmiotSzczegoly = new Intent(PrzedmiotList.this, PrzedmiotSzczegoly.class);
                        przedmiotSzczegoly.putExtra("PrzedmiotId",adapter.getRef(position).getKey());       //wysylanie przedmiotu iId do next activity
                        startActivity(przedmiotSzczegoly);
                    }
                });

            }
        };

        recyclerView.setAdapter(adapter);

    }
}
