package com.frskynet.mymarketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AboutActivity extends Activity {

    private TextView about_activity_desc;
    private AdView adView;
    private BannerAdEvents bannerAdEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about_activity_desc = findViewById(R.id.about_activity_desc);
        about_activity_desc.setText("A bazaar list is a rundown of things should have been bought by a customer. Buyers frequently order a bazaar rundown of food supplies to buy on the following visit to the market. The bazaar list was known 2000 years B.C. in antiquated Mesopotamia. There are enduring instances of Roman and Biblical shopping records. \n" +
                "\n" +
                "The bazaar show itself might be basically a piece bit of paper or something more intricate. There are cushions with magnets for keeping a gradual rundown accessible at the home, commonly on the cooler, however any attractive clasp with pieces of paper can be utilized to accomplish a similar outcome. There is even a particular gadget that apportions a piece of paper from a move for use in a bazaar list. Some shopping baskets accompany a little clipboard to fit bazaar records on.");

        adView = findViewById(R.id.about_activity_banner_ad);
        MobileAds.initialize(this, this.getString(R.string.my_bazar_list_app_id)); //App Id from string values
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        bannerAdEvents = new BannerAdEvents();
        bannerAdEvents.loadAd(this.getApplicationContext(), adView);
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    public void goToHomeBtnClick(View view) {
        Intent intent = new Intent(AboutActivity.this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
