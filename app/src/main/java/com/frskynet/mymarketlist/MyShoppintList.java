package com.frskynet.mymarketlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyShoppintList extends Activity {
    private String[] name = {};
    private String[] quantity = {};
    private EditText itemName;
    private EditText itemQuantity;
    private TextView bazaar_list_item_list_date;
    private ListView lView;
    private ListAdapter lAdapter;
    private DBHelper dbHelper;
    private ArrayList<String> shoppingList;
    private List<String> myItem;
    private List<String> myItemQuantity;
    private Date date;
    private AdView adView;
    private BannerAdEvents bannerAdEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shoppint_list);

        lView = (ListView) findViewById(R.id.bazaar_list_item_list_view);
        itemName = (EditText) findViewById(R.id.bazaar_list_item_name_input);
        itemQuantity = (EditText) findViewById(R.id.bazaar_list_item_quantity_input);
        bazaar_list_item_list_date = (TextView) findViewById(R.id.bazaar_list_item_list_date);

        date = new Date();
        String[] myDate = date.toString().split(" ");
        bazaar_list_item_list_date.setText(myDate[0] + " " + myDate[1] + " " + myDate[2]);

        lAdapter = new ListAdapter(MyShoppintList.this, name, quantity);
        lView.setAdapter(lAdapter);

        dbHelper = new DBHelper(this, null, null, 1);

        myItem = new ArrayList<>();
        myItemQuantity = new ArrayList<>();

        adView = findViewById(R.id.my_bazaar_list_activity_banner_ad);
        MobileAds.initialize(this, this.getString(R.string.my_bazar_list_app_id)); //App Id from string values
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        bannerAdEvents = new BannerAdEvents();
        bannerAdEvents.loadAd(this.getApplicationContext(), adView);
    }

    public void addBtnClick(View view) {
        if (!itemName.getText().toString().isEmpty() && !itemQuantity.getText().toString().isEmpty()) {
            myItem.add(itemName.getText().toString().replaceAll("\\s",""));
            myItemQuantity.add(itemQuantity.getText().toString().replaceAll("\\s",""));

            name = new String[myItem.size()];
            name = myItem.toArray(name);
            quantity = new String[myItemQuantity.size()];
            quantity = myItemQuantity.toArray(quantity);

            lAdapter = new ListAdapter(MyShoppintList.this, name, quantity);
            lView.setAdapter(lAdapter);

            itemName.setText("");
            itemQuantity.setText("");
        } else {
            Toast.makeText(this, "Please give an item name and quantity", Toast.LENGTH_LONG).show();
        }
    }

    public void getBtnClick(View view) {
        shoppingList = dbHelper.getAllList();
        System.out.println(shoppingList);
    }

    public void saveBtnClick(View view) {
        if(lView.getCount() > 0) {
            String itemNameListForDB = "";
            String itemQuantityListForDB = "";
            for(int i=0; i<myItem.size(); i++) {
                itemNameListForDB += myItem.get(i) + "__";
                itemQuantityListForDB += myItemQuantity.get(i) + "__";
            }
            String[] dd = date.toString().split(" ");
            String showDate = (dd[0] + " " + dd[1] + " " + dd[2] + " " + dd[5]);
            dbHelper.addShoppingItem(date.toString(), showDate, itemNameListForDB, itemQuantityListForDB);
            startActivity(new Intent(MyShoppintList.this, HistoryActivity.class));
        } else {
            Toast.makeText(this, "You have no item to save", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelBtnClick(View view) {
        name = new String[]{};
        quantity = new String[]{};

        lAdapter = new ListAdapter(MyShoppintList.this, name, quantity);
        lView.setAdapter(lAdapter);
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(MyShoppintList.this, MenuActivity.class);
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
