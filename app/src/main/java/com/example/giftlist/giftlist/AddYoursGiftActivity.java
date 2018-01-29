package com.example.giftlist.giftlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.giftlist.giftlist.Adapter.DatabaseHandler;
import com.example.giftlist.giftlist.Request.AddYourRequest;
import com.example.giftlist.giftlist.Request.DeleteRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class AddYoursGiftActivity extends AppCompatActivity {

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_yours_gift);


        Intent addintent = getIntent();
        final int id_prezentu = addintent.getIntExtra("id_prezentu", -1);
        final String nazwa = addintent.getStringExtra("nazwa");
        final String kategoria = addintent.getStringExtra("kategoria");
        final String opis = addintent.getStringExtra("opis");
        final String img = addintent.getStringExtra("img");
        final int cena = addintent.getIntExtra("cena", -1);

        final Button Button = (Button) findViewById(R.id.button);

        db = new DatabaseHandler(this);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor allcursor = db.getALLContacts();
                if (allcursor.getCount() == 0) {
                    //show message

                    Toast.makeText(getApplicationContext(),"baza danych jest pusta",Toast.LENGTH_SHORT).show();

                }

                StringBuffer buffer = new StringBuffer();
                while (allcursor.moveToNext()) {
                    buffer.append(allcursor.getString(0));

                }

                //Show all data


                String id_uzytkownika = buffer.toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(AddYoursGiftActivity.this, "Add Succes", Toast.LENGTH_SHORT).show();
//                        Intent delintent = new Intent(DeleteYoursGiftActivity.this, MainActivity.class);
//                        DeleteYoursGiftActivity.this.startActivity(delintent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddYoursGiftActivity.this);
                                builder.setMessage("Add Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                AddYourRequest addYourRequest = new AddYourRequest(Integer.toString(id_prezentu), id_uzytkownika, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddYoursGiftActivity.this);
                queue.add(addYourRequest);

            }
        });
    }
}



