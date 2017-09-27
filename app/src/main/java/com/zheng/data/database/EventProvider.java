package com.zheng.data.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Naqing on 9/26/17.
 */

public class EventProvider extends ContentProvider {
    static final String AUTHORITY = "com.zheng.provider";
    static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/";

    private DatabaseHelper mDatabaseHelper;
    private static final int URI_TABLE_EVENTS = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(AUTHORITY, AlbumContract.TABLE_NAME, URI_TABLE_EVENTS);
    }

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (sUriMatcher.match(uri)) {
            case URI_TABLE_EVENTS:
                return mDatabaseHelper.getReadableDatabase().query(AlbumContract.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case URI_TABLE_EVENTS:
                return AlbumContract.MIME_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        switch (sUriMatcher.match(uri)) {
            case URI_TABLE_EVENTS:
                long result = mDatabaseHelper.getWritableDatabase().insert(AlbumContract.TABLE_NAME, AlbumContract.COLUMN_DESCRIPTION, values);
                return uri.buildUpon().appendPath(String.valueOf(result)).build();
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case URI_TABLE_EVENTS:
                return mDatabaseHelper.getWritableDatabase().delete(AlbumContract.TABLE_NAME, selection, selectionArgs);
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case URI_TABLE_EVENTS:
                return mDatabaseHelper.getWritableDatabase().update(AlbumContract.TABLE_NAME, values, selection, selectionArgs);
        }
        return 0;
    }
}
