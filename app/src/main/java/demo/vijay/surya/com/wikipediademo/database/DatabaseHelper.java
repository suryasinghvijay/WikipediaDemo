package demo.vijay.surya.com.wikipediademo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String COLUMN_ID = "columnId";
    private static final String COLUMN_NOTE = "columnNote";
    private static final String TABLE_NAME = "wikipediaDB";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wikipedia_db";
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT"
                    + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public long insertNote(String clickedData) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            values.put(COLUMN_NOTE, clickedData);
            if (null != db) {
                return db.insert(TABLE_NAME, null, values);
            } else
                return -10;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }


    public List<String> getAllNotes() {
        List<String> notes = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " +
                COLUMN_ID + " DESC";
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    String data = cursor.getString(cursor.getColumnIndex(COLUMN_NOTE));
                    notes.add(data);
                } while (cursor.moveToNext());
            }
        } finally {
            if (null != db) {
                db.close();
            }
            if (null != cursor) {
                cursor.close();
            }
        }
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void deleteAllData() {
        this.getReadableDatabase().execSQL("delete from " + TABLE_NAME);
    }
}