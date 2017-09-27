package com.zheng.data.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Naqing on 9/26/17.
 */

public class AlbumContract implements BaseColumns {
    public static final String TABLE_NAME = "albumitem";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FILENAME = "filename";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";
    static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY, "
            + COLUMN_TITLE + TEXT_TYPE + COMMA_SEP
            + COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP
            + COLUMN_FILENAME + TEXT_TYPE
            + ")";

    static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String MIME_TYPE = "vnd.android.cursor.dir/vnd." + EventProvider.AUTHORITY + "." + TABLE_NAME;
    private static final Uri CONTENT_URI = Uri.parse(EventProvider.CONTENT_URI_STRING + TABLE_NAME);

    public static Uri getContentUri() {
        return CONTENT_URI;
    }
}
