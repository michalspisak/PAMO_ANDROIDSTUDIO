package com.example.michalspisak.aplikacja;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.michalspisak.aplikacja.Databases.Database;
import com.example.michalspisak.aplikacja.Model.Przedmiot;
import com.example.michalspisak.aplikacja.Model.Zamowienie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PrzedmiotSzczegoly extends AppCompatActivity {

    TextView    przedmiot_name,przedmiot_price, przedmiot_description;
    ImageView   przedmiot_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton    btnCart;
    ElegantNumberButton numberButton;

    String  przedmiotId ="";

    FirebaseDatabase    database;
    DatabaseReference   przedmiot;

    Przedmiot biezacyPrzedmiot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przedmiot_szczegoly);

        //Firebase

        database = FirebaseDatabase.getInstance();
        przedmiot = database.getReference("Przedmiot");

        // inicjalizacja widoku

        numberButton    =   (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart =   (FloatingActionButton)findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Zamowienie(
                        przedmiotId,
                        biezacyPrzedmiot.getNazwa(),
                        numberButton.getNumber(),
                        biezacyPrzedmiot.getCena(),
                        biezacyPrzedmiot.getZnizka()
                ));

                Toast.makeText(PrzedmiotSzczegoly.this, "Dodano do koszyka!", Toast.LENGTH_SHORT).show();

            }
        });

        przedmiot_description   =   (TextView)findViewById(R.id.przedmiot_description);
        przedmiot_name   =   (TextView)findViewById(R.id.przedmiot_name);
        przedmiot_price   =   (TextView)findViewById(R.id.przedmiot_price);
        przedmiot_image   =   (ImageView)findViewById(R.id.img_przedmiot);

        collapsingToolbarLayout =   (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        // przedmiot id from intent

        if(getIntent()!=null)
            przedmiotId = getIntent().getStringExtra("PrzedmiotId");
        if(!przedmiotId.isEmpty())
        {
            getSzczegolyPrzedmiot(przedmiotId);
        }
    }

    private void getSzczegolyPrzedmiot(String przedmiotId) {

        przedmiot.child(przedmiotId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                biezacyPrzedmiot   =   dataSnapshot.getValue(Przedmiot.class);

                //set zdjecie
                Picasso.with(getBaseContext()).load(biezacyPrzedmiot.getZdjecie())
                        .into(przedmiot_image);
                collapsingToolbarLayout.setTitle(biezacyPrzedmiot.getNazwa());

                przedmiot_price.setText(biezacyPrzedmiot.getCena());
                przedmiot_name.setText(biezacyPrzedmiot.getNazwa());
                przedmiot_description.setText(biezacyPrzedmiot.getOpis());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
