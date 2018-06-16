package com.example.michalspisak.aplikacja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.michalspisak.aplikacja.Common.Common;
import com.example.michalspisak.aplikacja.Model.User;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import com.rengwuxian.materialedittext.MaterialEditText;



public class ZalogowanoActivity extends AppCompatActivity {
    EditText    edtPhone,edtPassword;
    Button  btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zalogowano);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        // Inicjalizacja bazy Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Uzytkownik");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(ZalogowanoActivity.this);
                mDialog.setMessage("Prosze Czekac...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Sprawdzanie czy uzytkownika nie ma w bazie

                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {


                            // Informacja o userze
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setTelefon(edtPhone.getText().toString()); // set Telefon
                            if (user.getHaslo().equals(edtPassword.getText().toString())) {

                                Intent homeIntent = new Intent(ZalogowanoActivity.this,Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();
                            }
                            else {
                                mDialog.dismiss();
                                Toast.makeText(ZalogowanoActivity.this, "Podano zły numer lub hasło!", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else
                        {
                            Toast.makeText(ZalogowanoActivity.this, "Uzytkownika nie znaleziono!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
