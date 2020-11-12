package com.diana.penjualanperhiasan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.diana.penjualanperhiasan.R;
import com.diana.penjualanperhiasan.model.ModelPerhiasan;
import com.diana.penjualanperhiasan.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPerhiasan extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelPerhiasan> item;

    public AdapterPerhiasan(Activity activity, List<ModelPerhiasan> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_perhiasan, null);

        TextView kode = (TextView) convertView.findViewById(R.id.txtkodePerhiasan);
        TextView jenis = (TextView) convertView.findViewById(R.id.txtjenisPerhiasan);
        TextView kadar     = (TextView) convertView.findViewById(R.id.txtKrat);
        TextView berat          = (TextView) convertView.findViewById(R.id.txtBeratPerhiasan);
        TextView harga         = (TextView) convertView.findViewById(R.id.txtHargaPerhiasan);;
        ImageView gambarPerhiasan         = (ImageView) convertView.findViewById(R.id.gambarPerhiasan);

        kode.setText(item.get(position).getKodePerhiasan());
        jenis.setText(item.get(position).getJenisPerhiasan());
        berat.setText(item.get(position).getBeratPerhiasan());
        kadar.setText(item.get(position).getKadarPerhiasan());
        harga.setText("Rp." + item.get(position).getHargaPerhiasan());
       // kode.setText(item.get(position).getKodePerhiasan());
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarPerhiasan);
        return convertView;
    }
}
