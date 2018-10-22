package com.example.since85stas.level3.data.sql;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by seeyo on 20.10.2018.
 */

public final class GitContract {

    public static final String CONTENT_AUTHORITY = "com.example.since85stas.level3";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_GIT = "gits";

    public GitContract() {
    }

    public static final class GitEntry implements BaseColumns {

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GIT;

        public final static String TABLE_NAME = "git";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_DESCR = "descr";
        public final static String COLUMN_UPDATE = "last_update";
        public final static String COLUMN_SIZE = "size";
    }


}
