package com.example.wmt_wms_s23;

public final class SalesRecordContract {
    private SalesRecordContract() {} // To prevent accidental instantiation

    // Define the table schema
    public static class SalesRecordEntry {
        public static final String TABLE_NAME = "sales_records";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_SALESPERSON_ID = "salesperson_id";
        public static final String COLUMN_NAME_MONTH = "month";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_REGION = "region";
        public static final String COLUMN_NAME_SALES_AMOUNT = "sales_amount";
    }

    // Define SQL commands to create and delete the table
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SalesRecordEntry.TABLE_NAME + " (" +
                    SalesRecordEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    SalesRecordEntry.COLUMN_NAME_SALESPERSON_ID + " INTEGER," +
                    SalesRecordEntry.COLUMN_NAME_MONTH + " INTEGER," +
                    SalesRecordEntry.COLUMN_NAME_YEAR + " INTEGER," +
                    SalesRecordEntry.COLUMN_NAME_REGION + " TEXT," +
                    SalesRecordEntry.COLUMN_NAME_SALES_AMOUNT + " REAL)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SalesRecordEntry.TABLE_NAME;
}
