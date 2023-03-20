package com.example.lab4memopadusingsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase1.db";
    private static final String MEMOS = "memos";

    public static final String COLUMN_ID = "_id";
    public static final String MEMO_EXPLANATION = "explanation";

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MEMOS_TABLE = "CREATE TABLE memos (_id integer primary key autoincrement, name text)";
        db.execSQL(CREATE_MEMOS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MEMOS);
        onCreate(db);

    }

    public void addMemo(Contact c) {

        ContentValues values = new ContentValues();
        values.put(MEMO_EXPLANATION, c.getName());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(MEMOS, null, values);
        db.close();

    }

    public String addExampleContacts() {

        addMemo(new Contact("Martha C Zermeno"));
        addMemo(new Contact("Dorothy R George"));
        addMemo(new Contact("Amber C Hockman"));

        return("Contacts Added");

    }

    public String deleteAllContacts() {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(MEMOS, null, null);

        return("Contacts Deleted");

    }

    public Contact getContact(int id) {

        String query = "SELECT * FROM " + MEMOS + " WHERE " + COLUMN_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Contact c = null;

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            int newId = cursor.getInt(0);
            String newName = cursor.getString(1);
            String newAddress = cursor.getString(2);
            cursor.close();
            c = new Contact(newId, newName, newAddress);
        }

        db.close();
        return c;

    }

    public String getAllMemos() {

        String query = "select * from " + MEMOS;
        StringBuilder s = new StringBuilder();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                s.append(getContact(id)).append("\n");
            }
            while ( cursor.moveToNext() );
        }

        db.close();
        return s.toString();

    }

}
