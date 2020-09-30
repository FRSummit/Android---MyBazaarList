package com.frskynet.mymarketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MenuActivity extends Activity {
    private AdView adView;
    private BannerAdEvents bannerAdEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        adView = findViewById(R.id.menu_activity_banner_ad);
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

    public void myShoppingListBtnClick(View view) {
        Intent intent = new Intent(MenuActivity.this, MyShoppintList.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void historyBtnClick(View view) {
//        Intent intent = new Intent(MenuActivity.this, HistoryActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void addNewBtnClick(View view) {
//        Intent intent = new Intent(MenuActivity.this, AddNewItemActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void aboutBtnClick(View view) {
        Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void ratingBtnClick(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    public void moreAppBtnClick(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=$FRSummit$&")));
    }

    public void exitBtnClick(View view) {
        finish();
        System.exit(0);
    }
}
