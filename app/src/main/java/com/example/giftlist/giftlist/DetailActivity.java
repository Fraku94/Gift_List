package com.example.giftlist.giftlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent intent = getIntent();
        final int id_prezentu = intent.getIntExtra("id_prezentu", -1);
        final  String nazwa = intent.getStringExtra("nazwa");
        final String kategoria = intent.getStringExtra("kategoria");
        final String opis = intent.getStringExtra("opis");
        final String img = intent.getStringExtra("img");
        final int cena = intent.getIntExtra("cena", -1);

        TextView name = (TextView) findViewById(R.id.name);
        TextView categoria = (TextView) findViewById(R.id.categoria);
        TextView opiss = (TextView) findViewById(R.id.opiss);
        TextView price = (TextView) findViewById(R.id.price);
        ImageView image = (ImageView) findViewById(R.id.image);
        Button pokazid = (Button) findViewById(R.id.pokazid);
        Button dodaj = (Button)findViewById(R.id.dodajprezent);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent addintent = new Intent(DetailActivity.this, AddYoursGiftActivity.class);
                addintent.putExtra("id_prezentu", id_prezentu);
                addintent.putExtra("nazwa", nazwa);
                addintent.putExtra("kategoria", kategoria);
                addintent.putExtra("cena", cena);
                addintent.putExtra("opis", opis);
                addintent.putExtra("img", img);
                startActivity(addintent);
            }
        });
pokazid.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent addintent = new Intent(DetailActivity.this, DeleteYoursGiftActivity.class);
        addintent.putExtra("id_prezentu", id_prezentu);
        addintent.putExtra("nazwa", nazwa);
        addintent.putExtra("kategoria", kategoria);
        addintent.putExtra("cena", cena);
        addintent.putExtra("opis", opis);
        addintent.putExtra("img", img);
        startActivity(addintent);
    }
});
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
