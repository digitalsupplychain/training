package com.schroeder.walter.shoplist;

import android.provider.BaseColumns;

/**
 * Created by wschroeder on 20/02/2018.
 *
 * SQLs to local Database
 *
 */

public final class ShopListDatabaseContract {

    private ShopListDatabaseContract() {}

    public static final class ItemInfoEntry implements BaseColumns {
        public static final String TABLE_NAME="item_info";
        public static final String COLUMN_ITEM_ID ="item_id";
        public static final String COLUMN_ITEM_DESCRIPTION ="item_descritpion";
        public static final String COLUMN_ITEM_PRICE ="item_price";
        public static final String COLUMN_ITEM_IMAGE ="item_image";

        // CREATE INDEX item_info_index1 ON item_info (item_descritpion)
        public static final String  INDEX1 = TABLE_NAME + "_index1";
        public static final String SQL_CREATE_INDEX1 =
                "CREATE INDEX " + INDEX1 + " ON " + TABLE_NAME +
                        "(" + COLUMN_ITEM_DESCRIPTION + ")";

        public static final String getQName(String columnName){
            return TABLE_NAME + "." + columnName;
        }

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_ITEM_ID + " TEXT, " +
                        COLUMN_ITEM_DESCRIPTION + " TEXT NOT NULL, " +
                        COLUMN_ITEM_PRICE + " NUMERIC, " +
                        COLUMN_ITEM_IMAGE + " BLOB)";

    }

    public static final class ListInfoEntry implements BaseColumns{
        public static final String TABLE_NAME = "list_info";
        public static final String COLUMN_LIST_ID = "list_id";
        public static final String COLUMN_LIST_DESCRIPTION ="list_descritpion";
        public static final String COLUMN_LIST_DATE = "list_date";

        public static final String  INDEX1 = TABLE_NAME + "_index1";
        public static final String SQL_CREATE_INDEX1 =
                "CREATE INDEX " + INDEX1 + " ON " + TABLE_NAME +
                        "(" + COLUMN_LIST_DESCRIPTION + ")";

        public static final String getQName(String columnName){
            return TABLE_NAME + "." + columnName;
        }

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_LIST_ID + " TEXT NOT NULL, " +
                        COLUMN_LIST_DESCRIPTION + " TEXT, " +
                        COLUMN_LIST_DATE + " TEXT NOT NULL)";
    }

    public static final class ListsInfoEntry implements BaseColumns{
        public static final String TABLE_NAME = "lists_info";
        public static final String COLUMN_LIST_ID = "list_id";
        public static final String COLUMN_ITEM_ID = "item_id";
        public static final String COLUMN_ITEM_QTY = "item_qty";

        public static final String  INDEX1 = TABLE_NAME + "_index1";
        public static final String SQL_CREATE_INDEX1 =
                "CREATE INDEX " + INDEX1 + " ON " + TABLE_NAME +
                        "(" + COLUMN_LIST_ID + ")";

        public static final String getQName(String columnName){
            return TABLE_NAME + "." + columnName;
        }

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_LIST_ID + " TEXT NOT NULL, " +
                        COLUMN_ITEM_ID + " TEXT NOT NULL, " +
                        COLUMN_ITEM_QTY + " INTEGER)";

        public static final String PATH = "item";
    }
}
