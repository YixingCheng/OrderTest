package com.example.ethan.ordertest;

import android.provider.BaseColumns;

/**
 * Created by ethan on 12/21/14.
 */
public final class DatabaseContract {

    public DatabaseContract(){

    }

    public static abstract class dishEntry implements BaseColumns{
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_DISH_ID = "dishID";
        public static final String COLUMN_NAME_DISH_CATEOGRY = "dishCategory";
        public static final String COLUMN_NAME_DISH_NAME = "dishName";
        public static final String COLUMN_NAME_DISH_PRICE = "dishPrice";
        public static final String COLUMN_NAME_DISH_IMGPATH = "imgPath";
    }

}
