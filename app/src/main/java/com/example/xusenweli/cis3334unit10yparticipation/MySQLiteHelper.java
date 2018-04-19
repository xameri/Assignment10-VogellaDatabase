package com.example.xusenweli.cis3334unit10yparticipation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/*The clas is a helper nd creates database
Constant variables are created
* */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments"; // the name of the table
    public static final String COLUMN_ID = "_id";           // the column name of the primery key
    public static final String COLUMN_COMMENT = "comment";  // the column name of the comment field
    public static final String COLUMN_RATING = "rating";  // the column name of the rate field

    private static final String DATABASE_NAME = "commments.db";
    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    // SQL TABLE CREATE
    // Create table comments
    // Cooment_id string
    // Comment string
    // Rate string

    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null, " + COLUMN_RATING
            + " text not null );";

    // The constructor of this class with parameter
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}
