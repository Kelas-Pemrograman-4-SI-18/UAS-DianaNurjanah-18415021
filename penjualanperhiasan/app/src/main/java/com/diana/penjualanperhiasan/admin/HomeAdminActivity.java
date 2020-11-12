package com.diana.penjualanperhiasan.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.diana.penjualanperhiasan.R;
import com.diana.penjualanperhiasan.session.PrefSetting;
import com.diana.penjualanperhiasan.session.SessionManager;
import com.diana.penjualanperhiasan.users.loginActivity;

public class HomeAdminActivity extends AppCompatActivity {

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;
    CardView cardExit, cardDataPerhiasan, cardInputPerhiasan, cardProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferances();

        session = new SessionManager(HomeAdminActivity.this);

        prefSetting.isLogin(session, prefs);


      cardExit = (CardView) findViewById(R.id.cardExit);
      cardDataPerhiasan = (CardView) findViewById(R.id.cardDataPerhiasan);
      cardInputPerhiasan = (CardView) findViewById(R.id.cardInputPerhiasan);
      cardProfile = (CardView) findViewById(R.id.cardProfile);

      cardExit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              session.setLogin(false);
              session.setSessid(0);
              Intent i = new Intent(HomeAdminActivity.this, loginActivity.class);
              startActivity(i);
              finish();
          }
      });

        cardDataPerhiasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, ActivityDataPerhiasan.class);
                startActivity(i);
                finish();
            }
        });

        cardInputPerhiasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, InputDataPerhiasan.class);
                startActivity(i);
                finish();
            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, Profile.class);
                startActivity(i);
                finish();
            }
        });
    }
}
