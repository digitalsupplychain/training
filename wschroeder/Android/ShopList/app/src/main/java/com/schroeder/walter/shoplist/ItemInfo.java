package com.schroeder.walter.shoplist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wschroeder on 20/02/2018.
 *
 * Item Class
 *
 */

public final class ItemInfo implements Parcelable {
    private final String mItemId;
    private final String mDescription;

    public ItemInfo(String itemId, String description) {
        mItemId = itemId;
        mDescription = description;
    }

    public ItemInfo(Parcel source) {
        mItemId = source.readString();
        mDescription = source.readString();
    }

    public String getItemId() {
        return mItemId;
    }

    public String getDescription() {
        return mDescription;
    }


    @Override
    public String toString() {
        return mDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemInfo that = (ItemInfo) o;

        return mItemId.equals(that.mItemId);

    }

    @Override
    public int hashCode() {
        return mItemId.hashCode();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mItemId);
        dest.writeString(mDescription);
    }

    public static final Parcelable.Creator<ItemInfo> CREATOR =
            new Parcelable.Creator<ItemInfo>() {

                @Override
                public ItemInfo createFromParcel(Parcel source) {
                    return new ItemInfo(source);
                }

                @Override
                public ItemInfo[] newArray(int size) {
                    return new ItemInfo[size];
                }
            };
}
