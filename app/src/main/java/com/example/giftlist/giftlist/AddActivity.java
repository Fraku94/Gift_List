package com.example.giftlist.giftlist;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.giftlist.giftlist.Request.AddRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText name = (EditText) findViewById(R.id.nazwa);
        final EditText category = (EditText) findViewById(R.id.kategoria);
        final EditText price = (EditText) findViewById(R.id.cena);
        final EditText descryption = (EditText) findViewById(R.id.opis);
        final Button AddButton = (Button) findViewById(R.id.AddButton);


        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name = name.getText().toString();
                final String Category = category.getText().toString();
                final String Price = price.getText().toString();
                final String Descryption = descryption.getText().toString();

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(AddActivity.this, "Add Succes", Toast.LENGTH_SHORT).show();
                                Intent addintent = new Intent(AddActivity.this, MainActivity.class);
                                AddActivity.this.startActivity(addintent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
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

                AddRequest addRequest = new AddRequest(Name, Category, Price,Descryption, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddActivity.this);
                queue.add(addRequest);
            }
        });
    }
}