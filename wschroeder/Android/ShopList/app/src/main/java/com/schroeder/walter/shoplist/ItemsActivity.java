package com.schroeder.walter.shoplist;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import com.schroeder.walter.shoplist.ShopListDatabaseContract.ItemInfoEntry;
import com.schroeder.walter.shoplist.ShopListProviderContract.Items;


/**
 * Created by wschroeder on 20/02/2018.
 *
 * Items Activity shows a List of all available Items
 *
 */

public class ItemsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ShopListOpenHelper mDbOpenHelper;
    public static final String NOTE_ID = "com.schroeder.walter.notekeeper.NOTE_ID";
    private ItemRecyclerAdapter mItemRecyclerAdapter;
    public static final int LOADER_ITEMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemsActivity.this, ItemActivity.class));
            }
        });

        mDbOpenHelper = new ShopListOpenHelper(this);

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(LOADER_ITEMS, null, this);
    }

    private void initializeDisplayContent() {
        DataManager.loadFromDatabase(mDbOpenHelper);

        RecyclerView recycleItem = (RecyclerView) findViewById(R.id.list_items);
        mItemRecyclerAdapter = new ItemRecyclerAdapter(this, null);
        LinearLayoutManager itemLayerManager = new LinearLayoutManager(this);

        recycleItem.setAdapter(mItemRecyclerAdapter);
        recycleItem.setLayoutManager(itemLayerManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = null;
        if (id == LOADER_ITEMS) {

            final String[] noteColumns = {
                    ItemInfoEntry._ID,
                    ItemInfoEntry.COLUMN_ITEM_DESCRIPTION,
                    ItemInfoEntry.COLUMN_ITEM_PRICE
            };

            String noteOrderBy = ItemInfoEntry._ID;

            loader = new CursorLoader(this, Items.CONTENT_URI, noteColumns,
                    null, null, noteOrderBy);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(loader.getId() == LOADER_ITEMS)
            mItemRecyclerAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if(loader.getId() == LOADER_ITEMS)
            mItemRecyclerAdapter.changeCursor(null);
    }
}
