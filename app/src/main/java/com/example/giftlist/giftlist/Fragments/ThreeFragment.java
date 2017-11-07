package com.example.giftlist.giftlist.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.giftlist.giftlist.Adapter.CustomAdapter;
import com.example.giftlist.giftlist.Data.MyDataGifts;
import com.example.giftlist.giftlist.Data.User;
import com.example.giftlist.giftlist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;

import static com.example.giftlist.giftlist.MainActivity.USER;
import static com.example.giftlist.giftlist.MainActivity.USER_ID;


public class ThreeFragment extends Fragment {


    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<MyDataGifts> data_list;
    private Long userId;
    private User user;


    public ThreeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getArguments();
        if(bundle !=null) {
            if(bundle.containsKey(USER_ID)) {
                userId = bundle.getLong(USER_ID, 0L);
            }
            if(bundle.containsKey(USER)) {
                user = (User) bundle.getSerializable(USER);
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        data_list  = new ArrayList<>();
        load_data_from_server(0);

        gridLayoutManager = new GridLayoutManager(getActivity(),2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(getActivity(),data_list);
        recyclerView.setAdapter(adapter);

        Toast.makeText(getContext(), String.format(Locale.getDefault(), "UserID: %d, UserName:%s", userId, user.getUsename()), Toast.LENGTH_SHORT).show();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == data_list.size()-1){
                    load_data_from_server(data_list.get(data_list.size()-1).getId());
                }

            }
        });

        return view;
    }

    private void load_data_from_server(int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://giftproject.cba.pl/showGifts.php?id="+integers[0])
                        .build();
                try {
                    okhttp3.Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());



                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        MyDataGifts data = new MyDataGifts(
                                object.getInt("id_prezentu"),
                                object.getString("nazwa"),
                                object.getString("kategoria"),
                                object.getInt("cena"),
                                object.getString("opis"),
                                object.getString("img"));

                        data_list.add(data);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }

}
