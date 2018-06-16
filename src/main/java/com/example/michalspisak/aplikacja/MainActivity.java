package com.example.michalspisak.aplikacja;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);


        txtSlogan = (TextView)findViewById(R.id.txtSlogan);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/iceland.ttf");
        txtSlogan.setTypeface(face);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

            }


        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent  ZarejestrujActivity = new Intent(MainActivity.this, ZarejestrujActivity.class);
                startActivity(ZarejestrujActivity);
            }


        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  ZalogowanoActivity = new Intent(MainActivity.this, ZalogowanoActivity.class);
                startActivity(ZalogowanoActivity);
            }
        });


    }
}
