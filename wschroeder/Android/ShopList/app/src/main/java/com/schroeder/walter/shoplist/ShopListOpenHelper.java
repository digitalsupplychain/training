package com.schroeder.walter.shoplist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.schroeder.walter.shoplist.ShopListDatabaseContract.ItemInfoEntry;
import com.schroeder.walter.shoplist.ShopListDatabaseContract.ListInfoEntry;
import com.schroeder.walter.shoplist.ShopListDatabaseContract.ListsInfoEntry;

/**
 * Created by wschroeder on 20/02/2018.
 *
 * Database creation with Samples
 *
 */

public final class ShopListOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ShopList.db";
    public static final int DATABASE_VERSION = 1;

    public ShopListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ItemInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(ItemInfoEntry.SQL_CREATE_INDEX1);

        db.execSQL(ListInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(ListInfoEntry.SQL_CREATE_INDEX1);

        db.execSQL(ListsInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(ListsInfoEntry.SQL_CREATE_INDEX1);


        DatabaseDataWorker worker = new DatabaseDataWorker(db);
        worker.insertItemSample();
        worker.insertListSample();
        worker.insertListsSample();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
