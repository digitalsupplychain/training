package com.schroeder.walter.shoplist;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.schroeder.walter.shoplist.ShopListDatabaseContract.ItemInfoEntry;
import com.schroeder.walter.shoplist.ShopListProviderContract.Items;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by wschroeder on 20/02/2018.
 *
 * Create or Modify on Item
 *
 */

public class ItemActivity extends AppCompatActivity{

    private Uri mItemUri;
    private int mItemId;
    private EditText mtextItemDescription;
    private EditText mtextItemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ImageView image = (ImageView) findViewById(R.id.img_ItemImage);
//        image.setImageResource(R.drawable.manzana);

    }



    public  void save() {
//        FileInputStream fis = new FileInputStream("/storage/sdcard/demoImage.jpg");
//        byte[] image= new byte[fis.available()];
//        fis.read(image);
//        fis.close();

        mtextItemDescription = (EditText)  findViewById(R.id.editText_Item);
        mtextItemPrice = (EditText)  findViewById(R.id.editText_Price);

        insertNoteToDatabase(mtextItemDescription.getText().toString(),
                             mtextItemPrice.getText().toString());
    }

    private void insertNoteToDatabase(String itemDescription, String itemPrice){
        final ContentValues insertValues = new ContentValues();

        insertValues.put(ItemInfoEntry.COLUMN_ITEM_DESCRIPTION,itemDescription);
        insertValues.put(ItemInfoEntry.COLUMN_ITEM_PRICE,itemPrice);

        getContentResolver().insert(Items.CONTENT_URI, insertValues);
    }

    private void saveItemToDatabase(String itemId, String itemDescription, String itemPrice){
        final ContentValues values = new ContentValues();

        values.put(ItemInfoEntry.COLUMN_ITEM_ID,itemId);
        values.put(ItemInfoEntry.COLUMN_ITEM_DESCRIPTION,itemDescription);
        values.put(ItemInfoEntry.COLUMN_ITEM_PRICE,itemPrice);

        mItemUri = ContentUris.withAppendedId(Items.CONTENT_URI, mItemId);
        getContentResolver().update(mItemUri, values, null, null);
    }
}
