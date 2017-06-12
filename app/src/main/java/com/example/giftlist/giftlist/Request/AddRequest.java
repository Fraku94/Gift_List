package com.example.giftlist.giftlist.Request;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://giftproject.cba.pl/Addgift.php";
    private Map<String, String> params;

    public AddRequest(String Name, String Category, String Price,String Descryption, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nazwa", Name);
        params.put("kategoria", Category);
        params.put("cena", Price);
        params.put("opis", Descryption);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

