package com.diana.penjualanperhiasan.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.diana.penjualanperhiasan.R;
import com.diana.penjualanperhiasan.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailPerhiasan extends AppCompatActivity {

    EditText edtKodePerhiasan, edtJenisPerhiasan, edtKadarPerhiasan, edtBeratPerhiasan, edtHargaPerhiasan;
    ImageView imgGambarBuku;

    String strKodePerhiasan, strJenisPerhiasan, strKadarPerhiasan, strBeratPerhiasan, strHargaPerhiasan, strGamabr, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_perhiasan);

        edtKodePerhiasan = (EditText) findViewById(R.id.edtKodePerhiasan);
        edtJenisPerhiasan = (EditText) findViewById(R.id.edtJenisPerhiasan);
        edtKadarPerhiasan = (EditText) findViewById(R.id.edtKadarPerhiasan);
        edtBeratPerhiasan = (EditText) findViewById(R.id.edtBeratPerhiasan);
        edtHargaPerhiasan = (EditText) findViewById(R.id.edtHargaPerhiasan);

        imgGambarBuku = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strKodePerhiasan = i.getStringExtra("kodePerhiasan");
        strJenisPerhiasan = i.getStringExtra("jenisPerhiasan");
        strKadarPerhiasan = i.getStringExtra("kadarPerhiasan");
        strBeratPerhiasan = i.getStringExtra("beratPerhiasan");
        strHargaPerhiasan = i.getStringExtra("hargaPerhiasan");
        strGamabr = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtKodePerhiasan.setText(strKodePerhiasan);
        edtJenisPerhiasan.setText(strJenisPerhiasan);
        edtKadarPerhiasan.setText(strKadarPerhiasan);
        edtBeratPerhiasan.setText(strBeratPerhiasan);
        edtHargaPerhiasan.setText(strHargaPerhiasan);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGamabr)
                .into(imgGambarBuku);
    }
}