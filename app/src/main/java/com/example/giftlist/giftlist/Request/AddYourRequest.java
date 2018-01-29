package com.example.giftlist.giftlist.Request;

/**
 * Created by Z710 on 2018-01-30.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddYourRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://giftproject.cba.pl/AddYoursGift.php";
    private Map<String, String> params;

    public AddYourRequest(String id_prezentu, String id_uzytkownika, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_uzytkownika", id_uzytkownika);
        params.put("id_prezentu", id_prezentu);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
