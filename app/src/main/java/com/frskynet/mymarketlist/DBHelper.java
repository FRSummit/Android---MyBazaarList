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
        String QUERY;
//        QUERY = "DROP TABLE IF EXISTS shopping_list";
//        db.execSQL(QUERY);
        QUERY = "CREATE TABLE IF NOT EXISTS shopping_list (_id varchar(40) primary key, show_date varchar(30), item_list varchar(255), item_list_quantity varchar(255))";
        System.out.println("Executing " + QUERY);
        db.execSQL(QUERY);
    }

    public void removeAllTable() {
        String QUERY = "DROP TABLE IF EXISTS shopping_list";
        this.getWritableDatabase().execSQL(QUERY);
    }

    public void addShoppingItem(String id, String date, String itemList, String itemQuantityList) {
        String QUERY = "INSERT INTO shopping_list VALUES ('" + id + "', '" + date + "', '" + itemList + "', '" + itemQuantityList + "');";
        System.out.println("Executing " + QUERY);
        this.getWritableDatabase().execSQL(QUERY);
    }

    public ArrayList<String> getAllList() {
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> showDate = new ArrayList<>();
        ArrayList<String> itemName = new ArrayList<>();
        ArrayList<String> itemQuantity = new ArrayList<>();
        String QUERY = "SELECT * from shopping_list";
        Cursor cursor = this.getWritableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
//            words.add(cursor.getString(1));
            id.add(cursor.getString(cursor.getColumnIndex("_id")));
            showDate.add(cursor.getString(cursor.getColumnIndex("show_date")));
            itemName.add(cursor.getString(cursor.getColumnIndex("item_list")));
            itemQuantity.add(cursor.getString(cursor.getColumnIndex("item_list_quantity")));
            cursor.moveToNext();
        }
        return id;
    }

    public ArrayList<String> getAllListDate() {
        ArrayList<String> showDate = new ArrayList<>();
        String QUERY = "SELECT * from shopping_list";
        Cursor cursor = this.getWritableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            showDate.add(cursor.getString(cursor.getColumnIndex("_id")));
            cursor.moveToNext();
        }
        return showDate;
    }

    public Cursor getSpecificData(String data) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> showDate = new ArrayList<>();
        Cursor res =  db.rawQuery( "select * from shopping_list where _id = '" + data + "'", null );
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
