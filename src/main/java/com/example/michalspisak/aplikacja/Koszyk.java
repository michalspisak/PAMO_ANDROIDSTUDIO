package com.example.michalspisak.aplikacja;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michalspisak.aplikacja.Common.Common;
import com.example.michalspisak.aplikacja.Databases.Database;
import com.example.michalspisak.aplikacja.Model.Zamowienie;
import com.example.michalspisak.aplikacja.Model.Zlecenie;
import com.example.michalspisak.aplikacja.ViewHolder.KoszykAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Koszyk extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;




    FirebaseDatabase database;
    DatabaseReference zlecenia ;

    TextView txtTotalPrice;
    FButton btnPlace;

    List<Zamowienie> koszyk = new ArrayList<>();

    KoszykAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koszyk);


        database = FirebaseDatabase.getInstance();
        zlecenia=database.getReference("Zlecenia");


        //Inizjalicaja

        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView)findViewById(R.id.total);
        btnPlace = (FButton)findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ShowAlertDialog();


            }
        });

        loadListPrzedmiot();
    }

    private void loadListPrzedmiot() {
        koszyk  =   new Database(this).getCarts();
        adapter = new KoszykAdapter(koszyk , this);
        recyclerView.setAdapter(adapter);

        //kalkulator

        int total = 0;
        for(Zamowienie zamowienie:koszyk)
            total+=(Integer.parseInt(zamowienie.getCena()))*(Integer.parseInt(zamowienie.getIlosc()));
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));
    }

    private void ShowAlertDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Koszyk.this);
        alertDialog.setTitle("Pozostal jeden krok!");
        alertDialog.setMessage("Wprowadz adres dostarczenia: ");

        final EditText edtAdress = new EditText(Koszyk.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT

        );
        edtAdress.setLayoutParams(lp);
        alertDialog.setView(edtAdress); // dodanie tekstu edytowalnego dla uzytkownika aby mogl wpisac swoj adres
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // tworzenie nowego zlecenia


                Zlecenie zlecenie = new Zlecenie(


                        Common.currentUser.getTelefon(),
                        Common.currentUser.getNazwa(),
                        edtAdress.getText().toString(),
                        txtTotalPrice.getText().toString(),
                        koszyk


                );




                // zatwierdzenie i wrzucenie do Firebase
                //System.CurrentMilli

                zlecenia.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(zlecenie);








                //usuniecie przedmiotow z koszyka
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Koszyk.this, "Dziękujemy!, Zlecenie przyjęto to realizacji!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDialog.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();


    }





}
