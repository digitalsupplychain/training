package com.schroeder.walter.shoplist;

import android.net.Uri;
import android.provider.BaseColumns;

import com.schroeder.walter.shoplist.ShopListDatabaseContract.ItemInfoEntry;

/**
 * Created by wschroeder on 21/02/2018.
 *
 * Public  local Database access configuration
 *
 */

public final class ShopListProviderContract {
    private ShopListProviderContract(){}
    public static final String AUTHORITY = "com.schroeder.walter.shoplist.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    protected interface ItemColumns {
        public static final String COLUMN_ITEM_DESCRIPTION = ItemInfoEntry.COLUMN_ITEM_DESCRIPTION;
        public static final String COLUMN_ITEM_PRICE = ItemInfoEntry.COLUMN_ITEM_PRICE;
    }

    public static final class Items implements BaseColumns, ItemColumns{
        public static final String PATH = "items";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
    }
}


