package com.frskynet.mymarketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyShoppintList extends Activity {

    private String[] name = {
            "My demo item"
    };
    private String[] quantity = {
            "5 Kg"
    };
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
    }

    public void addBtnClick(View view) {
        if (!itemName.getText().toString().isEmpty() && !itemQuantity.getText().toString().isEmpty()) {
            myItem.add(itemName.getText().toString());
            myItemQuantity.add(itemQuantity.getText().toString());

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
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> get btn clicked");
        shoppingList = dbHelper.getAllList();
        System.out.println(shoppingList);
    }

    public void saveBtnClick(View view) {
//        dbHelper.removeAllTable();
//        dbHelper.addShoppingItem("ARNOLD");
//        dbHelper.addShoppingItem("NICHOLAS");
//        dbHelper.addShoppingItem("JOHNY");
//        dbHelper.addShoppingItem("TIMMY");

//        System.out.println("Size : " + myItem);

//        dbHelper.addShoppingItem(date.toString(), myItem, myItemQuantity);
        shoppingList = dbHelper.getAllList();
        System.out.println(shoppingList);
        for (int i=0; i<shoppingList.size(); i++) {
            System.out.println(shoppingList.get(i));
        }


    }

    public void cancelBtnClick(View view) {
        name = new String[]{};
        quantity = new String[]{};

        lAdapter = new ListAdapter(MyShoppintList.this, name, quantity);
        lView.setAdapter(lAdapter);
    }
}