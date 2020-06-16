package com.example.since85stas.level3.data.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by seeyo on 20.10.2018.
 */

public class GitHelper extends SQLiteOpenHelper {


    /**
     * Имя файла базы данных
     */
    private static final String DATABASE_NAME = "hotel.db";

    /**
     * Версия базы данных. При изменении схемы увеличить на единицу
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Конструктор {@link GitHelper}.
     *
     * @param context Контекст приложения
     */
    public GitHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Вызывается при создании базы данных
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Строка для создания таблицы
        String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE " + GitContract.GitEntry.TABLE_NAME + " ("
                + GitContract.GitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + GitContract.GitEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + GitContract.GitEntry.COLUMN_DESCR + " TEXT, "
                + GitContract.GitEntry.COLUMN_UPDATE + " TEXT);";

        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    /**
     * Вызывается при обновлении схемы базы данных
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF EXISTS " + GitContract.GitEntry.TABLE_NAME);
        // Создаём новую таблицу
        onCreate(db);

    }



}
