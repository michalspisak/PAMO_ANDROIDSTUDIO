package com.example.michalspisak.aplikacja;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.michalspisak.aplikacja.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ZarejestrujActivity extends AppCompatActivity {

    MaterialEditText edtPhone,edtName,edtPassword;
    Button  btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zarejestruj);

        edtName = (MaterialEditText)findViewById(R.id.edtName);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        // Inicjalizacja bazy Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Uzytkownik");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(ZarejestrujActivity.this);
                mDialog.setMessage("Prosze Czekac...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()){

                            mDialog.dismiss();
                            Toast.makeText(ZarejestrujActivity.this, "Numer Telefonu jest juz w bazie", Toast.LENGTH_SHORT).show();


                        }
                        else
                        {

                            mDialog.dismiss();
                            User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(ZarejestrujActivity.this, "Brawo, zarejestrowalismy Twoj numer!", Toast.LENGTH_SHORT).show();
                            finish();

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
