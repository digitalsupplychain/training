package com.schroeder.walter.shoplist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import com.schroeder.walter.shoplist.ShopListDatabaseContract.ItemInfoEntry;

/**
 * Created by wschroeder on 20/02/2018.
 *
 * Create Database in Memory
 *
 */

public final class DataManager {

    private static DataManager ourInstance = null;

    private List<ItemInfo> mItem = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
        }
        return ourInstance;
    }

    public List<ItemInfo> getItem() {
        return mItem;
    }

    public static void loadFromDatabase(ShopListOpenHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        final String[] itemColumns = {
                ItemInfoEntry.COLUMN_ITEM_ID,
                ItemInfoEntry.COLUMN_ITEM_DESCRIPTION,
                ItemInfoEntry.COLUMN_ITEM_PRICE
        };
        final Cursor itemCursor = db.query(
                ItemInfoEntry.TABLE_NAME,
                itemColumns,
                null,
                null,
                null,
                null,
                ItemInfoEntry.COLUMN_ITEM_ID + " DESC"
        );

        loadItemFromDatabase(itemCursor);
    }

    private static void loadItemFromDatabase(Cursor cursor) {
        int itemIdPos = cursor.getColumnIndex(ItemInfoEntry.COLUMN_ITEM_ID);
        int itemDescriptionPos = cursor.getColumnIndex(ItemInfoEntry.COLUMN_ITEM_DESCRIPTION);
        int itemPricePos = cursor.getColumnIndex(ItemInfoEntry.COLUMN_ITEM_PRICE);

        DataManager dm = getInstance();
        dm.mItem.clear();
        while(cursor.moveToNext()){
            String itemId = cursor.getString(itemIdPos);
            String itemDescription = cursor.getString(itemDescriptionPos);
            String itemPrice = cursor.getString(itemPricePos);
            ItemInfo item = new ItemInfo(itemId,itemDescription);
            dm.mItem.add(item);
        }
        cursor.close();
    }
}
