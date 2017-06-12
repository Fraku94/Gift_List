package com.example.giftlist.giftlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent intent = getIntent();
        String nazwa = intent.getStringExtra("nazwa");
        String kategoria = intent.getStringExtra("kategoria");
        String opis = intent.getStringExtra("opis");
        String img = intent.getStringExtra("img");
        int cena = intent.getIntExtra("cena", -1);

        TextView name = (TextView) findViewById(R.id.name);
        TextView categoria = (TextView) findViewById(R.id.categoria);
        TextView opiss = (TextView) findViewById(R.id.opiss);
        TextView price = (TextView) findViewById(R.id.price);
        ImageView image = (ImageView) findViewById(R.id.image);


        name.setText(nazwa);
        categoria.setText(kategoria);
        opiss.setText(opis);
        price.setText(cena+"");
        new DownloadImageTask((ImageView) findViewById(R.id.image))
                .execute(img);


    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
