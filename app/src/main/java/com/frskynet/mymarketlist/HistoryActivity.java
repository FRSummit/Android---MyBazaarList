package com.frskynet.mymarketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class HistoryActivity extends Activity {
    private DBHelper dbHelper;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> idList;
    private AdView adView;
    private BannerAdEvents bannerAdEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.bazaar_history_list_view);

        dbHelper = new DBHelper(this, null, null, 1);
        idList = dbHelper.getAllListDate();

        arrayAdapter = new ArrayAdapter<String>(HistoryActivity.this, android.R.layout.simple_list_item_1, idList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(HistoryActivity.this, idList.get(position), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HistoryActivity.this, HistoryDetails.class);
                intent.putExtra("key", idList.get(position));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        adView = findViewById(R.id.history_activity_banner_ad);
        MobileAds.initialize(this, this.getString(R.string.my_bazar_list_app_id)); //App Id from string values
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        bannerAdEvents = new BannerAdEvents();
        bannerAdEvents.loadAd(this.getApplicationContext(), adView);
    }

    public void goToMenuFromHistory(View view) {
        Intent intent = new Intent(HistoryActivity.this, MenuActivity.class);
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
