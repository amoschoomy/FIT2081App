package com.example.autoshowroom.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.room.RoomDatabase;

public class MyContentProvider extends ContentProvider {
    public static final String CONTENT_AUTHORITY = "fit2081.app.AMOS/tasks";
    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final int MULTIPLE_ROWS_TASKS = 1;
    private static final int SINGLE_ROW_TASKS = 2;
    private static final int MULTIPLE_ROWS_USERS = 3;
    private static final int SINGLE_ROW_USERS = 4;
    RoomDatabase db;

    public MyContentProvider() {
    }

    private static UriMatcher createUriMatcher() {

        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;

        //sUriMatcher will return code 1 if uri like authority/tasks
        uriMatcher.addURI(authority, Car.TABLE_NAME, MULTIPLE_ROWS_TASKS);

        //sUriMatcher will return code 2 if uri like e.g. authority/tasks/7 (where 7 is id of row in tasks table)
        uriMatcher.addURI(authority, Car.TABLE_NAME + "/#", SINGLE_ROW_TASKS);

        //sUriMatcher will return code 1 if uri like authority/users
        uriMatcher.addURI(authority, "users", MULTIPLE_ROWS_USERS);

        //sUriMatcher will return code 2 if uri like e.g. authority/users/7 (where 7 is id of row in users table)
        uriMatcher.addURI(authority, "users" + "/#", SINGLE_ROW_USERS);

        return uriMatcher;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int deletionCount;
        int uriType = createUriMatcher().match(uri);
        deletionCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .delete(Car.TABLE_NAME, selection, selectionArgs);
        switch (uriType) {
            case MULTIPLE_ROWS_TASKS: //no trailing row id so selection may indicate more than 1 row needs to be deleted if they can be found
                deletionCount = db
                        .getOpenHelper()
                        .getWritableDatabase()
                        .delete(Car.TABLE_NAME, selection, selectionArgs);
                break;
            case SINGLE_ROW_TASKS: //trailing row id, so just one row to be deleted if it can be found
                String id = uri.getLastPathSegment();
                String selectionId = Car.COLUMN_ID + " = ?";
                String[] selectionArgsId = {String.valueOf(id)};
                deletionCount = db
                        .getOpenHelper()
                        .getWritableDatabase()
                        .delete(Car.TABLE_NAME, selectionId, selectionArgsId);
                break;
            default:
//                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return deletionCount;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long rowId = db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(Car.TABLE_NAME, 0, contentValues);
        return ContentUris.withAppendedId(CONTENT_URI, rowId);
    }

    @Override
    public boolean onCreate() {
        db = CarDatabase.getDatabase(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(Car.TABLE_NAME);
        String query = builder.buildQuery(projection, selection, null, null, sortOrder, null);
        final Cursor cursor = db
                .getOpenHelper()
                .getReadableDatabase()
                .query(query, selectionArgs);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int updateCount;
        updateCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(Car.TABLE_NAME, 0, values, selection, selectionArgs);
        return updateCount;
    }
}