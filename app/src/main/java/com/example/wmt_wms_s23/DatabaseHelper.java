package com.example.wmt_wms_s23;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Define database name and version
    private static final String DATABASE_NAME = "E_salesperson.db";
    private static final int DATABASE_VERSION = 1;

    // Define constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables in the database
        db.execSQL(SalespersonContract.SQL_CREATE_ENTRIES);
        db.execSQL(SalesRecordContract.SQL_CREATE_ENTRIES);

        // Insert a fixed user into the User table
        ContentValues userValues = new ContentValues();
        userValues.put(SalespersonContract.SalespersonEntry.COLUMN_NAME_USERNAME, "test");
        userValues.put(SalespersonContract.SalespersonEntry.COLUMN_NAME_PASSWORD, "test");
        long newRowId = db.insert(SalespersonContract.SalespersonEntry.TABLE_NAME, null, userValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade database when needed (e.g., when DATABASE_VERSION is incremented)
        db.execSQL(SalespersonContract.SQL_DELETE_ENTRIES);
        db.execSQL(SalesRecordContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    // Add a new salesperson to the database
    public long insertSalesperson(String name, String region) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SalespersonContract.SalespersonEntry.COLUMN_NAME_NAME, name);
        values.put(SalespersonContract.SalespersonEntry.COLUMN_NAME_REGION, region);
        long newRowId = db.insert(SalespersonContract.SalespersonEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }
    // Retrieve all salespersons from the database
    public ArrayList<Salesperson> getAllSalespersons() {
        ArrayList<Salesperson> salespersons = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                SalespersonContract.SalespersonEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(SalespersonContract.SalespersonEntry.COLUMN_NAME_NAME));
            @SuppressLint("Range") String region = cursor.getString(cursor.getColumnIndex(SalespersonContract.SalespersonEntry.COLUMN_NAME_REGION));
            Salesperson salesperson = new Salesperson(name, region);
            salespersons.add(salesperson);
        }
        cursor.close();
        db.close();
        return salespersons;
    }
}
