package com.example.giftlist.giftlist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.giftlist.giftlist.Adapter.DatabaseHandler;
import com.example.giftlist.giftlist.Data.User;
import com.example.giftlist.giftlist.Data.UsersDatabase;
import com.example.giftlist.giftlist.Request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    TextInputLayout tlUsername, tlPassword;
    Button bLogin;
    TextView tvSign;
    String username, password;
    DatabaseHandler db;
    RequestQueue requestQueue;
//    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//    final EditText etUsername = (EditText) findViewById(R.id.etUsername);
//    final EditText etPassword = (EditText) findViewById(R.id.etPassword);
//    final TextView tvSign = (TextView) findViewById(R.id.tvSign);
//    final Button bLogin = (Button) findViewById(R.id.bLogin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        initialize();
        db = new DatabaseHandler(this);
        requestQueue = Volley.newRequestQueue(LoginActivity.this);
        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regintent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regintent);

            }
        });
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = tlUsername.getEditText().getText().toString();
                password = tlPassword.getEditText().getText().toString();

                if (validateUsername(username) && validatePassword(password)) { //Username and Password Validation

                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("Logging You In");
                    progressDialog.setCancelable(false);
                    progressDialog.show();


                    LoginRequest loginRequest = new LoginRequest(username, password, new Response.Listener<String>() {


                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onResponse(String response) {
                            Log.i("Login Response", response);
                            progressDialog.dismiss();
                            //   json obcejt
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                if (jsonResponse.getBoolean("success")) {

                                    db.addContact(new UsersDatabase(Long.toString(jsonResponse.getLong("id")),
                                            "waldek", "dasdasd","asa@sa.pl"));


                                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    Long userId = jsonResponse.getLong("id");
                                    String username = jsonResponse.getString("username");
                                    User user = new User(userId, username);
// from server to  activity
                                    myIntent.putExtra(MainActivity.USER_ID, userId);
                                    myIntent.putExtra(MainActivity.USER, user);
                                    startActivity(myIntent);



                                    Toast.makeText(LoginActivity.this, "Log in", Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {
                                    if (jsonResponse.getString("status").equals("USERNAME"))
                                        Toast.makeText(LoginActivity.this, "User Not Found",
                                                Toast.LENGTH_SHORT).show();
                                    else {
                                        Toast.makeText(LoginActivity.this, "Password dont't match",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("MYAPP", "unexpected JSON exception", e);
                                Toast.makeText(LoginActivity.this, "Bad Response From Server", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            if (error instanceof ServerError)
                                Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                            else if (error instanceof TimeoutError)
                                Toast.makeText(LoginActivity.this, "Connection Timed Out", Toast.LENGTH_SHORT).show();
                            else if (error instanceof NetworkError)
                                Toast.makeText(LoginActivity.this, "Bad Network Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(loginRequest);

                }
            }
        });
        //
//
//                                OneFragment onefragment = new OneFragment();
//                                FragmentManager fragmentManager = getSupportFragmentManager();
//                                fragmentManager.beginTransaction()
//                                        .replace(R.id.frag,onefragment)
//                                        .commit();
    }

    //Validacja


        private void initialize() {
        tlUsername = (TextInputLayout) findViewById(R.id.tl_etUsername);
        tlPassword = (TextInputLayout) findViewById(R.id.tl_etPassword);
        tvSign = (TextView) findViewById(R.id.tvSign);
        bLogin = (Button) findViewById(R.id.bLogin);
    }

        private boolean validateUsername(String string) {

        if (string.equals("")) {
            tlUsername.setError("enter username");
            return false;
        } else if (string.length() > 10) {
            tlUsername.setError("max 10 ");
            return false;
        } else if (string.length() < 3) {
            tlUsername.setError("Min 3 characters");
            return false;
        }
        tlUsername.setErrorEnabled(false);
        return true;
    }

        private boolean validatePassword(String string) {

        if (string.equals("")) {
            tlPassword.setError("Enter Your Password");
            return false;
        } else if (string.length() > 10) {
            tlPassword.setError("max 10 characters");
            return false;
        } else if (string.length() < 3) {
        tlPassword.setError("minimum 3 characters");
        return false;
        }
        tlPassword.setErrorEnabled(false);
        return true;
        }
}


