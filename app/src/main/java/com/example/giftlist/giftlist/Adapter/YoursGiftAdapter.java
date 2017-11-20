package com.example.giftlist.giftlist.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.giftlist.giftlist.Data.MyDataGifts;
import com.example.giftlist.giftlist.Data.User;
import com.example.giftlist.giftlist.DetailActivity;
import com.example.giftlist.giftlist.LoginActivity;
import com.example.giftlist.giftlist.R;
import com.example.giftlist.giftlist.RegisterActivity;
import com.example.giftlist.giftlist.Request.DeleteRequest;
import com.example.giftlist.giftlist.Request.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.view.View.OnClickListener;
import static android.view.View.OnLongClickListener;


public class YoursGiftAdapter extends RecyclerView.Adapter<YoursGiftAdapter.ViewHolder> {

    private Context mContext;
    private List<MyDataGifts> my_data_Gifts;
//    private List<User> user;





    public YoursGiftAdapter(Context context, List<MyDataGifts> my_data) {
        mContext = context;
        this.my_data_Gifts = my_data;

    }

    @Override
    public YoursGiftAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_ygift,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final YoursGiftAdapter.ViewHolder holder, int position) {
        final MyDataGifts item = my_data_Gifts.get(position);
//        final User useritem = user.get(position);

// przycik do doawani prezentow zmeinai narazie sie ikonka trzeba zrobic obsluge bazy




        holder.description.setText(item.getNazwa());
        holder.cena.setText(Integer.toString(item.getCena()));


        Glide.with(mContext).load(item.getImage_link()).into(holder.imageView);



        holder.more.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("nazwa", item.getNazwa());
                intent.putExtra("kategoria", item.getKategoria());
                intent.putExtra("cena", item.getCena());
                intent.putExtra("opis", item.getOpis());
                intent.putExtra("img", item.getImage_link());
                mContext.startActivity(intent);
            }
        });
        holder.like.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                final String id_prezentu = Integer.toString(item.getId());
//                final String id_uzytkownika =  Long.toString(useritem.getId());
//
//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//                            if (success) {
//                                Toast.makeText(mContext, "Remove Your Gift", Toast.LENGTH_SHORT).show();
//
//                            } else {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                                builder.setMessage("Error")
//                                        .setNegativeButton("Retry", null)
//                                        .create()
//                                        .show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                DeleteRequest deleteRequest = new DeleteRequest(id_prezentu, id_uzytkownika, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(mContext);
//                queue.add(deleteRequest);
            }
        });
        holder.mView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "You long clicked "+ item.getNazwa(),
                        Toast.LENGTH_SHORT).show();
                return false;



            }
        });
    }



    @Override
    public int getItemCount() {
        return my_data_Gifts.size();
    }


    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView description;
        public TextView cena;
        public ImageView imageView,more,like;
        public View mView;


        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            cena = (TextView) itemView.findViewById(R.id.price);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            mView = itemView;
            more =(ImageView) itemView.findViewById(R.id.more);
            like =(ImageView) itemView.findViewById(R.id.like);





        }
    }
}
