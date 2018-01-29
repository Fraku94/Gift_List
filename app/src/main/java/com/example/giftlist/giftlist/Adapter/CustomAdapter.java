package com.example.giftlist.giftlist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.giftlist.giftlist.Adapter.CustomAdapter;
import com.example.giftlist.giftlist.Data.MyDataGifts;
import com.example.giftlist.giftlist.DetailActivity;
import com.example.giftlist.giftlist.R;

import java.util.List;

import static android.view.View.OnClickListener;
import static android.view.View.OnLongClickListener;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context mContext;
    private List<MyDataGifts> my_data_Gifts;
    ImageView more;
    boolean isPressed = false;



    public CustomAdapter(Context context, List<MyDataGifts> my_data) {
        mContext = context;
        this.my_data_Gifts = my_data;

    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final CustomAdapter.ViewHolder holder, int position) {
        final MyDataGifts item = my_data_Gifts.get(position);

// przycik do doawani prezentow zmeinai narazie sie ikonka trzeba zrobic obsluge bazy

        holder.description.setText(item.getNazwa());
        holder.cena.setText(Integer.toString(item.getCena()));


        Glide.with(mContext).load(item.getImage_link()).into(holder.imageView);



        holder.more.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("id_prezentu", item.getId_prezentu());
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
                if (isPressed) {
                    holder.like.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                    Toast.makeText(mContext, "Teraz idz do swoich prezentow", Toast.LENGTH_LONG).show();
                }else {
                    holder.like.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                    Toast.makeText(mContext, "Jednak nie kupisz", Toast.LENGTH_LONG).show();
                }
                isPressed = !isPressed;
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
