package com.example.giftlist.giftlist.Request;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://giftproject.cba.pl/DeleteYoursGifts.php";
    private Map<String, String> params;

    public DeleteRequest(String id_prezentu, String id_uzytkownika, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_prezentu", id_prezentu);
        params.put("id_uzytkownika", id_uzytkownika);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
