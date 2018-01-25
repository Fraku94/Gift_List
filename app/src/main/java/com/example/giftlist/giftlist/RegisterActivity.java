package com.example.giftlist.giftlist;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.giftlist.giftlist.Request.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;



public class RegisterActivity extends AppCompatActivity {
    TextInputLayout til_username, til_password, til_confirmPass, til_email;
    String username, password,email,confirm;
    Button bRegister;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Ceate Account");
        initialize();
        //Tworzenie zapytania
        requestQueue = Volley.newRequestQueue(RegisterActivity.this);
//        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
//        final EditText ettUsername = (EditText) findViewById(R.id.etUsername);
//        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
//        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = til_email.getEditText().getText().toString();
                username = til_username.getEditText().getText().toString();
                password = til_password.getEditText().getText().toString();
                confirm=til_confirmPass.getEditText().getText().toString();
                //sprawdzenie porawności danych walidacja

                if (
                    validateEmail(email) &&
                            validatePassword(password) &&
                            validateConfirm(confirm) &&
                            validateUsername(username)
                        ){
                    final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("Creating Your Account");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    // Sukces walidacji

                    RegisterRequest registerRequest = new RegisterRequest(email,username,password, new  Response.Listener<String>()
                    {
                        @Override
                    public void onResponse(String response) {
                            Log.i("Response", response);
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonResponse = new JSONObject(response);

                                if (jsonResponse.getBoolean("success")) {
                                    Toast.makeText(RegisterActivity.this, "account create", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    if (jsonResponse.getString("status").equals("user exisist"))
                                        Toast.makeText(RegisterActivity.this, "User  exist",
                                                Toast.LENGTH_SHORT).show();
                                 else{

                                    Toast.makeText(RegisterActivity.this, "Wrong.Try again", Toast.LENGTH_SHORT).show();
                                }
                            }

                            }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                });
                requestQueue.add(registerRequest);
            }
                                     }
    });
}
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//                            if (success) {
//                                Toast.makeText(RegisterActivity.this, "Account Create", Toast.LENGTH_SHORT).show();
//                                Intent regintent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                RegisterActivity.this.startActivity(regintent);
//                            } else {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//                                builder.setMessage("Register Failed")
//                                        .setNegativeButton("Retry", null)
//                                        .create()
//                                        .show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();




    private void initialize() {
        //Initializing the widgets in the layout
        til_password = (TextInputLayout) findViewById(R.id.tl_etPasswordReg);
        til_confirmPass = (TextInputLayout) findViewById(R.id.tl_etConfirmReg);
        til_username = (TextInputLayout) findViewById(R.id.tl_etUsernameReg);
        til_email = (TextInputLayout) findViewById(R.id.tl_EmailReg);
        bRegister = (Button) findViewById(R.id.bRegister);

    }

    private boolean validateUsername(String string) {
        //VWalidacja wprowadzania danych użytkownika
        if (string.equals("")) {
            til_username.setError("enter username");
            return false;
        } else if (string.length() > 10) {
            til_username.setError("max 10 ");
            return false;
        } else if (string.length() < 3) {
            til_username.setError("Min 3 characters");
            return false;
        }
        til_username.setErrorEnabled(false);
        return true;
    }

    private boolean validatePassword(String string) {
        //VWalidacja wprowadzania danych użytkownika
        if (string.equals("")) {
            til_password.setError("Enter Your Password");
            return false;
        } else if (string.length() > 10) {
            til_password.setError("max 10 characters");
            return false;
        } else if (string.length() < 3) {
            til_password.setError("minimum 3 characters");
            return false;
        }
        til_password.setErrorEnabled(false);
        return true;
    }
    private boolean validateConfirm(String string) {
        if (string.equals("")) {
            til_confirmPass.setError("reEnter your password");
            return false;
        } else if (!string.equals(til_password.getEditText().getText().toString())) {
            til_confirmPass.setError("passwords do Not match");
            til_password.setError("passwords do not match");
            return false;
        }
        til_confirmPass.setErrorEnabled(false);
        return true;
    }
    private boolean validateEmail(String string) {
        if (string.equals("")) {
            til_email.setError("Enter Your Email Address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(string).matches()) {
            til_email.setError("Enter A Valid Email Address");
            return false;
        }
        til_email.setErrorEnabled(false);
        return true;
    }
}

