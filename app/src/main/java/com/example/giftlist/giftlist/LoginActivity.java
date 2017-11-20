package com.example.giftlist.giftlist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.giftlist.giftlist.Data.User;
import com.example.giftlist.giftlist.Request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvSign = (TextView) findViewById(R.id.tvSign);
        final Button bLogin = (Button) findViewById(R.id.bLogin);


        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regintent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(regintent);

            }
        });
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Long userId = jsonResponse.getLong("id");
                                String username = jsonResponse.getString("username");
                                User user = new User(userId, username);
                                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                                myIntent.putExtra(MainActivity.USER_ID, userId);
                                myIntent.putExtra(MainActivity.USER, user);

                                startActivity(myIntent);
                                Toast.makeText(LoginActivity.this, "Log in",
                                        Toast.LENGTH_SHORT).show();
//
//
//
//                                OneFragment onefragment = new OneFragment();
//                                FragmentManager fragmentManager = getSupportFragmentManager();
//                                fragmentManager.beginTransaction()
//                                        .replace(R.id.frag,onefragment)
//                                        .commit();


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}


