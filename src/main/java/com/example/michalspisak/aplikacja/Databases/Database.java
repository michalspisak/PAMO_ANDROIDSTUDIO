package com.example.michalspisak.aplikacja.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.michalspisak.aplikacja.Model.Zamowienie;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME="MilitaryShop.db";
    private static final int DB_VER=1;
    public Database(Context context) {
        super(context,DB_NAME,null,DB_VER);
    }

    public List<Zamowienie> getCarts()
    {
        SQLiteDatabase  db  = getReadableDatabase();
        SQLiteQueryBuilder  qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"ProduktNazwa", "ProduktId", "Ilosc", "Cena", "Znizka"};
        String sqlTable ="SzczegolyZamowienia";

        qb.setTables(sqlTable);
        Cursor  c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Zamowienie> result   =   new ArrayList<>();
        if(c.moveToFirst())
        {

            do{
                result.add(new Zamowienie(c.getString(c.getColumnIndex("ProduktId")),
                        c.getString(c.getColumnIndex("ProduktNazwa")),
                        c.getString(c.getColumnIndex("Ilosc")),
                        c.getString(c.getColumnIndex("Cena")),
                        c.getString(c.getColumnIndex("Znizka"))
                ));
            }while (c.moveToNext());

        }
        return result;

    }

    public void addToCart (Zamowienie zamowienie)
    {

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO SzczegolyZamowienia(ProduktId, ProduktNazwa, Ilosc, Cena, Znizka) VALUES ('%s','%s','%s','%s','%s');",
                zamowienie.getProduktId(),
                zamowienie.getProduktNazwa(),
                zamowienie.getIlosc(),
                zamowienie.getCena(),
                zamowienie.getZnizka());
        db.execSQL(query);


    }

    public void cleanCart ()
    {

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM SzczegolyZamowienia");
        db.execSQL(query);

    }
}


