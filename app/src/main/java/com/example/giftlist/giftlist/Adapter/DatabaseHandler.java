package com.example.giftlist.giftlist.Adapter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.giftlist.giftlist.Data.UsersDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z710 on 2018-01-29.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //Wszystkie zmienne statyczne
    //wersja bazy danych

    private static final int DATABASE_VERSION = 1;

    //Nazwa bazy danych

    private static final String DATABASE_NAME = "UserDatabaseMenager";

    //Nazwa tabeli kontaktów

    private static final String TABLE_USER = "UserDatabase";

    //Nazwy koumn tabeli kontakty

    private static final String KEY_ID = "id";
    private static final String KEY_Username = "username";
    private static final String KEY_Password = "password";
    private static final String KEY_Email = "email";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER +
                "("
                + KEY_ID + " TEXT,"
                + KEY_Username + " TEXT,"
                + KEY_Password + " TEXT,"
                + KEY_Email + " TEXT" +
                ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Usuniecie starszej tabeli jesli istnieje
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        //Stworzenie tabeli ponowanie
        onCreate(db);
    }
    // Dodawanie nowych kontaktów
    public void addContact(UsersDatabase usersDatabase){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        //Stworzenie tabeli ponowanie
        onCreate(db);
        ContentValues values = new ContentValues();
        values.put(KEY_ID,usersDatabase.get_id());                 //ID uzytownika
        values.put(KEY_Username,usersDatabase.get_username());
        values.put(KEY_Password,usersDatabase.get_password());
        values.put(KEY_Email, usersDatabase.get_email());       //Email

        //Dodawanie wierszy

        db.insert(TABLE_USER, null,values);
        db.close();                                             // zamkniecie połączenie z bazą
    }
    //dosatnie wszystkich kontaktów
    public Cursor getALLContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor allcursor = db.rawQuery("SELECT * FROM " + TABLE_USER,null);
        return allcursor;
    }
}