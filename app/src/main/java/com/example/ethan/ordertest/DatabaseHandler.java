package com.example.ethan.ordertest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ethan.ordertest.DatabaseContract.dishEntry;

import java.util.ArrayList;

/**
 * Created by ethan on 12/20/14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String    TAG                 = "OrderTest";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DatabaseManager";

    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + dishEntry.TABLE_NAME + " (" +
                    dishEntry.COLUMN_NAME_DISH_ID + " INTEGER PRIMARY KEY," +
                    dishEntry.COLUMN_NAME_DISH_CATEOGRY + TEXT_TYPE + COMMA_SEP +
                    dishEntry.COLUMN_NAME_DISH_NAME + TEXT_TYPE + COMMA_SEP +
                    dishEntry.COLUMN_NAME_DISH_IMGPATH + TEXT_TYPE + COMMA_SEP +
                    dishEntry.COLUMN_NAME_DISH_PRICE + REAL_TYPE +
            ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + dishEntry.TABLE_NAME;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(TAG, "in constructor");
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "before create db");
        //String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
        //        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
        //        + KEY_PH_NO + " TEXT," + KEY_EMAIL + " TEXT" + ")";
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.i(TAG, "after create db");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL(SQL_DELETE_ENTRIES);
        // Create tables again
        onCreate(db);
    }

    public String[] getCategory(){

        SQLiteDatabase db = this.getReadableDatabase();
        Log.i(TAG, "before cursor");
        String select = "SELECT DISTINCT " + dishEntry.COLUMN_NAME_DISH_CATEOGRY + " from " + dishEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        String[] catList = new String[cursor.getCount()];
        int catIndex = cursor.getColumnIndex(dishEntry.COLUMN_NAME_DISH_CATEOGRY);
        int j = 0;
        if (cursor.moveToFirst()) {
            do {
                catList[j] = cursor.getString(catIndex);
                j++;
            } while (cursor.moveToNext());
        }
        Log.i(TAG, " we have columns:" + Integer.toString(cursor.getCount()));
        cursor.close();
        db.close();
        return catList;
    }

    public ArrayList<Dish> getDishbyCat(String category){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(dishEntry.TABLE_NAME, new String[]{ dishEntry.COLUMN_NAME_DISH_ID,
                                                                     dishEntry.COLUMN_NAME_DISH_CATEOGRY,
                                                                     dishEntry.COLUMN_NAME_DISH_NAME,
                                                                     dishEntry.COLUMN_NAME_DISH_IMGPATH,
                                                                     dishEntry.COLUMN_NAME_DISH_PRICE},
                                                                     dishEntry.COLUMN_NAME_DISH_CATEOGRY + "=?",
                                                                     new String[]{category}, null, null, null, null);
        ArrayList<Dish> dishes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Dish dish = new Dish();
                dish.setID(Integer.parseInt(cursor.getString(0)));
                dish.setCategory(cursor.getString(1));
                dish.setName(cursor.getString(2));
                dish.setPath(cursor.getString(3));
                dish.setPrice(Float.parseFloat(cursor.getString(4)));
                dishes.add(dish);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dishes;
    }
}
