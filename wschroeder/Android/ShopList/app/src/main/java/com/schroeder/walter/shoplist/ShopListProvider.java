package com.schroeder.walter.shoplist;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.schroeder.walter.shoplist.ShopListDatabaseContract.ItemInfoEntry;
import com.schroeder.walter.shoplist.ShopListProviderContract.Items;

import static com.schroeder.walter.shoplist.ShopListProviderContract.AUTHORITY;

/**
 * Created by wschroeder on 21/02/2018.
 *
 * Public SQL Statements to local Database
 *
 */

public class ShopListProvider extends ContentProvider {

    private ShopListOpenHelper mDbOpenHelper;
    private static final String MIME_VENDOR_TYPE = "vnd." + AUTHORITY + ".";
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int ITEMS = 1;
    public static final int ITEMS_ROW = 2;

    static {
        sUriMatcher.addURI(AUTHORITY, Items.PATH, ITEMS);
        sUriMatcher.addURI(AUTHORITY, Items.PATH + "/#", ITEMS_ROW);
    }

    public ShopListProvider() {}

    @Override
    public boolean onCreate() {
        mDbOpenHelper = new ShopListOpenHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        String mimeType = null;
        int uriMatch = sUriMatcher.match(uri);
        switch(uriMatch){
            case ITEMS:
                mimeType = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + MIME_VENDOR_TYPE + Items.PATH;
                break;
        }
        return mimeType;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();

        int uriMatch = sUriMatcher.match(uri);
        switch (uriMatch) {
            case ITEMS:
                cursor = db.query(ItemInfoEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        long rowId = -1;
        Uri rowUri = null;
        int uriMatch = sUriMatcher.match(uri);
        switch (uriMatch){
            case ITEMS:
                rowId = db.insert(ItemInfoEntry.TABLE_NAME, null, values);
//              content://com.schroeder.walter.notekeeper.provider/notes/1
                rowUri = ContentUris.withAppendedId(Items.CONTENT_URI, rowId);
                break;
        }
        return rowUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        long rowId = -1;
        String rowSelection = null;
        String[] rowSelectionArgs = null;
        int nRows = -1;
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();

        int uriMatch = sUriMatcher.match(uri);
        switch(uriMatch) {
            case ITEMS:
                nRows = db.delete(ItemInfoEntry.TABLE_NAME, selection, selectionArgs);
                break;
        }

        return nRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        long rowId = -1;
        String rowSelection = null;
        String[] rowSelectionArgs = null;
        int nRows = -1;
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();

        int uriMatch = sUriMatcher.match(uri);
        switch(uriMatch) {
            case ITEMS:
                nRows = db.update(ItemInfoEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case ITEMS_ROW:
                rowId = ContentUris.parseId(uri);
                rowSelection = ItemInfoEntry._ID + " = ?";
                rowSelectionArgs = new String[]{Long.toString(rowId)};
                nRows = db.update(ItemInfoEntry.TABLE_NAME, values, rowSelection, rowSelectionArgs);
                break;
        }
        return nRows;
    }
}
