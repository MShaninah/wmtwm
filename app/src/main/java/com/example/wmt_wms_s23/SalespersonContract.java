package com.example.wmt_wms_s23;

public final class SalespersonContract {
    private SalespersonContract() {} // To prevent accidental instantiation

    // Define the table schema
    public static class SalespersonEntry {
        public static final String TABLE_NAME = "salespersons";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_REGION = "region";
        public static final String COLUMN_NAME_PHOTO = "photo";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_ADMINISTRATOR = "administrator";
    }

    // Define SQL commands to create and delete the table
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SalespersonEntry.TABLE_NAME + " (" +
                    SalespersonEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    SalespersonEntry.COLUMN_NAME_NAME + " TEXT," +
                    SalespersonEntry.COLUMN_NAME_REGION + " TEXT," +
                    SalespersonEntry.COLUMN_NAME_PHOTO + " TEXT," +
                    SalespersonEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    SalespersonEntry.COLUMN_NAME_PASSWORD + " TEXT," +
                    SalespersonEntry.COLUMN_NAME_ADMINISTRATOR + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SalespersonEntry.TABLE_NAME;
}
