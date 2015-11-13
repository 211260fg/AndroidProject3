package com.example.floriangoeteyn.androidproject3.persistentie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jeroen on 5/11/2015.
 */
public class DictionaryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_NAME = "gebruiker";
    private static final String DATABASE_NAME = "dbEva";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";
    private static final String COMMA = ",";

    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    "ID INT NOT NULL PRIMARY KEY," +
                    "email" + TEXT_TYPE + COMMA +
                    "wachtwoord" + TEXT_TYPE + COMMA +
                    "facebookId" + TEXT_TYPE + COMMA +
                    "dagGestart" + TEXT_TYPE + ");";

    public DictionaryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int x, int y) {

    }

}
