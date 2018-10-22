package com.example.since85stas.level3.data.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.since85stas.level3.data.RepositoriesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seeyo on 20.10.2018.
 */

public class GitDataSourse {

    private static final String TAG = "gitsourse";


    private GitHelper dbHelper;
    private SQLiteDatabase database;

    private String[] notesAllColumn = {
            GitContract.GitEntry._ID,
            GitContract.GitEntry.COLUMN_NAME,
            GitContract.GitEntry.COLUMN_DESCR,
            GitContract.GitEntry.COLUMN_UPDATE
    };

    public GitDataSourse(Context context) {
        dbHelper = new GitHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public int addRep(RepositoriesModel model) {
        ContentValues values = new ContentValues();
        values.put(GitContract.GitEntry.COLUMN_NAME, model.getName());
        values.put(GitContract.GitEntry.COLUMN_DESCR, model.getDescription());
        values.put(GitContract.GitEntry.COLUMN_UPDATE, model.getUpdated_at());
        long insertId = database.insert(GitContract.GitEntry.TABLE_NAME, null,
                values);
//        RepositoriesModel model = new RepositoriesModel();
//        model .setName(title);
//        model .setDescription(descr);
//        model.setUpdated_at(upd  );
        Log.d(TAG, "addRep: " + insertId);
        return (int)insertId;
    }

    public int saveListToDb (List<RepositoriesModel> repoList) {
        int count = 0;
        for (int i = 0; i < repoList.size() ; i++) {
            int id = addRep(repoList.get(i));
            count ++;
        }
        return count;
    }

//    public void editNote(long id, String note, String title) {
//        ContentValues editedNote = new ContentValues();
//        editedNote.put(dbHelper.COLUMN_ID, id);
//        editedNote.put(dbHelper.COLUMN_NOTE, note);
//        editedNote.put(dbHelper.COLUMN_TITLE, title);
//
//        database.update(dbHelper.TABLE_NOTES,
//                editedNote,
//                dbHelper.COLUMN_ID + "=" + id,
//                null);
//    }

//    public void deleteNote(Note note) {
//        long id = note.getId();
//        database.delete(DatabaseHelper.TABLE_NOTES, DatabaseHelper.COLUMN_ID
//                + " = " + id, null);
//    }

    public void deleteAll() {
        database.delete(GitContract.GitEntry.TABLE_NAME, null, null);
    }

    public List<RepositoriesModel> getAllNotes() {
        List<RepositoriesModel> repos = new ArrayList<RepositoriesModel>();

        Cursor cursor = database.query(GitContract.GitEntry.TABLE_NAME,
                notesAllColumn, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            RepositoriesModel model = cursorToModel(cursor);
            repos.add(model);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return repos;
    }

    private RepositoriesModel cursorToModel(Cursor cursor) {
        RepositoriesModel rep = new RepositoriesModel();
        rep.setName(cursor.getString(0));
        rep.setDescription(cursor.getString(1));
        rep.setUpdated_at(cursor.getString(2));
        return rep;
    }

}
