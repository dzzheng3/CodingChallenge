package com.zheng.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.zheng.data.database.AlbumContract;


public class AlbumItem implements Parcelable {

    public AlbumItem(Cursor cursor) {
       createEvent(cursor);
    }

    @SerializedName("description")
    private String mDescription;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("filename")
    private String mFilename;

    public AlbumItem(String mDescription, String mTitle, String mFilename) {
        this.mDescription = mDescription;
        this.mTitle = mTitle;
        this.mFilename = mFilename;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmFilename() {
        return mFilename;
    }

    public void setmFilename(String mFilename) {
        this.mFilename = mFilename;
    }

    protected AlbumItem(Parcel in) {
        mDescription = in.readString();
        mTitle = in.readString();
        mFilename = in.readString();
    }

    public static final Creator<AlbumItem> CREATOR = new Creator<AlbumItem>() {
        @Override
        public AlbumItem createFromParcel(Parcel in) {
            return new AlbumItem(in);
        }

        @Override
        public AlbumItem[] newArray(int size) {
            return new AlbumItem[size];
        }
    };

    public String getDescription() {
        return mDescription;
    }


    public String getTitle() {
        return mTitle;
    }









    private void createEvent(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex(AlbumContract.COLUMN_TITLE);
        if (columnIndex != -1) {
            mTitle = cursor.getString(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(AlbumContract.COLUMN_DESCRIPTION);
        if (columnIndex != -1) {
            mDescription = cursor.getString(columnIndex);
        }



        columnIndex = cursor.getColumnIndex(AlbumContract.COLUMN_FILENAME);
        if (columnIndex != -1) {
            mFilename = cursor.getString(columnIndex);
        }
    }

    public ContentValues createContentValues() {
        ContentValues values = new ContentValues();
        values.put(AlbumContract.COLUMN_TITLE, mTitle);
        values.put(AlbumContract.COLUMN_DESCRIPTION, mDescription);
        values.put(AlbumContract.COLUMN_FILENAME, mFilename);
        return values;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDescription);
        dest.writeString(mTitle);
        dest.writeString(mFilename);

    }
}
