package com.frskynet.mymarketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class HistoryDetails extends Activity {
    private TextView dateText;
    private ListView listView;
    private DBHelper dbHelper;
    private ListAdapter listAdapter;
    private String[] itemName;
    private String[] itemQuantity;
    private String intentExtra;
    private AdView adView;
    private BannerAdEvents bannerAdEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        dateText = (TextView) findViewById(R.id.bazaar_history_details_date);
        listView = findViewById(R.id.bazaar_list_item_list_view);

        Intent intentText= getIntent();
        Bundle extraText = intentText.getExtras();
        if(extraText != null) {
            intentExtra = (String) extraText.get("key");
        }
        System.out.println(intentExtra);
        dateText.setText(intentExtra);

        dbHelper = new DBHelper(this, null, null, 1);
        Cursor cursor = dbHelper.getSpecificData(intentExtra);
        cursor.moveToFirst();
        System.out.println(cursor.getString(cursor.getColumnIndex("item_list")));
        System.out.println(cursor.getString(cursor.getColumnIndex("item_list_quantity")));

        String[] itemNameParts = cursor.getString(cursor.getColumnIndex("item_list")).split("__");
        String[] itemQuantityParts = cursor.getString(cursor.getColumnIndex("item_list_quantity")).split("__");

        itemName = new String[itemNameParts.length];
        itemQuantity = new String[itemQuantityParts.length];

        for (int i=0; i<itemNameParts.length; i++) {
            itemName[i] = itemNameParts[i];
            itemQuantity[i] = itemQuantityParts[i];
        }

        listAdapter = new ListAdapter(HistoryDetails.this, itemName, itemQuantity);
        listView.setAdapter(listAdapter);

        adView = findViewById(R.id.history_details_activity_banner_ad);
        MobileAds.initialize(this, this.getString(R.string.my_bazar_list_app_id)); //App Id from string values
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        bannerAdEvents = new BannerAdEvents();
        bannerAdEvents.loadAd(this.getApplicationContext(), adView);
    }

    public void goToHistory(View view) {
        Intent intent = new Intent(HistoryDetails.this, HistoryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
}
