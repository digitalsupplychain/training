package com.schroeder.walter.shoplist;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.schroeder.walter.shoplist.ShopListDatabaseContract.ItemInfoEntry;
import com.schroeder.walter.shoplist.ShopListDatabaseContract.ListInfoEntry;
import com.schroeder.walter.shoplist.ShopListDatabaseContract.ListsInfoEntry;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by wschroeder on 20/02/2018.
 *
 * Sample Information
 *
 */

public final class DatabaseDataWorker {
    private SQLiteDatabase mDb;

    public DatabaseDataWorker(SQLiteDatabase db) {
        mDb = db;
    }

    public void insertItemSample() {
//        Bitmap bitmap = BitmapFactory.decodeResource(, R.drawable.manzana);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//        byte[] image = bos.toByteArray();

        insertItem("I1", "Manzana", 1.99);
        insertItem("I2", "Pera", 2.99);
        insertItem("I3", "Banana", 3.99);
        insertItem("I4", "Melon", 4.99);
    }

    public void insertListSample() {
        insertList("L1", "Enero", "01/01/2018");
        insertList("L2", "Febrero", "01/02/2018");
        insertList("L3", "Marzo", "01/03/2018");
    }

    public void insertListsSample() {
        insertLists("L1", "I1", 1);

        insertLists("L2", "I1", 2);
        insertLists("L2", "I2", 3);

        insertLists("L3", "I1", 4);
        insertLists("L3", "I2", 5);
        insertLists("L3", "I3", 6);
    }

    private void insertItem(String itemId, String itemDescription, double itemPrice) {
        ContentValues values = new ContentValues();
        values.put(ItemInfoEntry.COLUMN_ITEM_ID, itemId);
        values.put(ItemInfoEntry.COLUMN_ITEM_DESCRIPTION, itemDescription);
        values.put(ItemInfoEntry.COLUMN_ITEM_PRICE, itemPrice);
//        values.put(ItemInfoEntry.COLUMN_ITEM_IMAGE, itemImage);

        long newRowId = mDb.insert(ItemInfoEntry.TABLE_NAME, null, values);
    }

    private void insertList(String listId, String listDescription, String listDate) {
        ContentValues values = new ContentValues();
        values.put(ListInfoEntry.COLUMN_LIST_ID, listId);
        values.put(ListInfoEntry.COLUMN_LIST_DESCRIPTION, listDescription);
        values.put(ListInfoEntry.COLUMN_LIST_DATE, listDate);

        long newRowId = mDb.insert(ListInfoEntry.TABLE_NAME, null, values);
    }

    private void insertLists(String listId, String itemId, int itemQty) {
        ContentValues values = new ContentValues();
        values.put(ListsInfoEntry.COLUMN_LIST_ID, listId);
        values.put(ListsInfoEntry.COLUMN_ITEM_ID, itemId);
        values.put(ListsInfoEntry.COLUMN_ITEM_QTY, itemQty);

        long newRowId = mDb.insert(ListsInfoEntry.TABLE_NAME, null, values);
    }
}
