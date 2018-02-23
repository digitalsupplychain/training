package com.schroeder.walter.shoplist;

/**
 * Created by wschroeder on 21/02/2018.
 *
 * Item Adapter and Holder
 * Connect local Database information with activities using a Cursor
 *
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.schroeder.walter.shoplist.ShopListDatabaseContract.ItemInfoEntry;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private Cursor mCursor;
    private final LayoutInflater mLayoutInflater;
    private int mItemIdPos;
    private int mItemDescriptionPos;
    private int mItemPricePos;
    private int mIdPos;
    
    public ItemRecyclerAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor  = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnsPositions();
    }

    public void changeCursor(Cursor cursor){
        if(mCursor != null)
            mCursor.close();
        mCursor = cursor;
        populateColumnsPositions();
        notifyDataSetChanged();
    }

    private void populateColumnsPositions() {
        if(mCursor == null)
            return;

//      Get column indexes from mCursor
        mItemDescriptionPos = mCursor.getColumnIndex(ItemInfoEntry.COLUMN_ITEM_DESCRIPTION);
        mItemPricePos = mCursor.getColumnIndex(ItemInfoEntry.COLUMN_ITEM_PRICE);
        mIdPos = mCursor.getColumnIndex(ItemInfoEntry._ID);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list,parent, false);
        return new ViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String itemId = mCursor.getString(mItemIdPos);
        String itemDescription = mCursor.getString(mItemDescriptionPos);
        String itemPrice = mCursor.getString(mItemPricePos);
        int id = mCursor.getInt(mIdPos);

        holder.mTextDescription.setText(itemDescription);
        holder.mTextPrice.setText(itemPrice);
        holder.mId = id;
    }
    
    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextDescription;
        public final TextView mTextPrice;
        public int mId;
        public int mCurrentPosition;
    
        public ViewHolder(View itemView) {
            super(itemView);
            mTextDescription = (TextView) itemView.findViewById(R.id.text_Description);
            mTextPrice = (TextView) itemView.findViewById(R.id.text_Price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,ItemsActivity.class);
                    intent.putExtra(ItemsActivity.NOTE_ID, mId);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
