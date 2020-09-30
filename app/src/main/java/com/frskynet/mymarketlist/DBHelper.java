package com.frskynet.mymarketlist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F R Summit on 30th September,2020
 * Simplexhub Limited
 * frsummit@simplexhub.com
 */
public class DBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "MyBazaarList.db";
    final static int DB_VERSION = 2;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
        onCreate(this.getWritableDatabase());
        System.out.println("Constructor got called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> onCreated get called");
        String QUERY;
//        QUERY = "DROP TABLE IF EXISTS shopping_list";
//        db.execSQL(QUERY);
        QUERY = "CREATE TABLE IF NOT EXISTS shopping_list (_id varchar(50) primary key, item_list varchar(255), item_list_quantity varchar(255))";
        System.out.println("Executing " + QUERY);
        db.execSQL(QUERY);
    }

    public void removeAllTable() {
        String QUERY = "DROP TABLE IF EXISTS shopping_list";
        this.getWritableDatabase().execSQL(QUERY);
    }

    public void addShoppingItem(String id, List<String> itemList, List<String> itemQuantityList) {
        String QUERY = "INSERT INTO shopping_list VALUES ('" + id + "', '" + itemList + "', '" + itemQuantityList + "');";
        System.out.println("Executing " + QUERY);
        this.getWritableDatabase().execSQL(QUERY);
    }

    public ArrayList<String> getAllList() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> getting all list");
        ArrayList<String> words = new ArrayList<>();
        String QUERY = "SELECT * from shopping_list";
        Cursor cursor = this.getWritableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
//            words.add(cursor.getString(1));
            words.add(cursor.getString(cursor.getColumnIndex("item_list")));
            cursor.moveToNext();
        }
        return words;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> onUpgrade get called");
    }
}
