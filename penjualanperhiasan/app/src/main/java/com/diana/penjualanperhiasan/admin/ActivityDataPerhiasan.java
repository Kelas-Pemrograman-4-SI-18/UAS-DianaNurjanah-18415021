package com.diana.penjualanperhiasan.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.diana.penjualanperhiasan.R;
import com.diana.penjualanperhiasan.adapter.AdapterPerhiasan;
import com.diana.penjualanperhiasan.model.ModelPerhiasan;
import com.diana.penjualanperhiasan.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityDataPerhiasan extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterPerhiasan adapter;
    ListView list;

    ArrayList<ModelPerhiasan> newsList = new ArrayList<ModelPerhiasan>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_perhiasan);

        getSupportActionBar().setTitle("Data Buku");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterPerhiasan(ActivityDataPerhiasan.this, newsList);
        list.setAdapter(adapter);
        getAllBuku();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ActivityDataPerhiasan.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllBuku() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataPerhiasan, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data buku = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelPerhiasan perhiasan = new ModelPerhiasan();
                                    final String _id = jsonObject.getString("_id");
                                    final String kodePerhiasan = jsonObject.getString("kodePerhiasan");
                                    final String jenisPerhiasan = jsonObject.getString("jenisPerhiasan");
                                    final String kadarPerhiasan = jsonObject.getString("kadarPerhiasan");
                                    final String beratPerhiasan = jsonObject.getString("beratPerhiasan");
                                    final String hargaPerhiasan = jsonObject.getString("hargaPerhiasan");
                                    final String gambar = jsonObject.getString("gambar");
                                    perhiasan.setKodePerhiasan(kodePerhiasan);
                                    perhiasan.setJenisPerhiasan(jenisPerhiasan);
                                    perhiasan.setKadarPerhiasan(kadarPerhiasan);
                                    perhiasan.setBeratPerhiasan(beratPerhiasan);
                                    perhiasan.setHargaPerhiasan(hargaPerhiasan);
                                    perhiasan.setGambar(gambar);
                                    perhiasan.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(ActivityDataPerhiasan.this, EditPerhiasanDanHapusActivity.class);
                                            a.putExtra("kodePerhiasan", newsList.get(position).getKodePerhiasan());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("jenisPerhiasan", newsList.get(position).getJenisPerhiasan());
                                            a.putExtra("kadarPerhiasan", newsList.get(position).getKadarPerhiasan());
                                            a.putExtra("beratPerhiasan", newsList.get(position).getBeratPerhiasan());
                                            a.putExtra("hargaPerhiasan", newsList.get(position).getHargaPerhiasan());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(perhiasan);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}