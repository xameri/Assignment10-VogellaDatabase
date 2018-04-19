package com.example.xusenweli.cis3334unit10yparticipation;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

// Comment data source class
public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT, MySQLiteHelper.COLUMN_RATING};

    // from farah
    //private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
    //MySQLiteHelper.COLUMN_COMMENT, MySQLiteHelper.COLUMN_RATING };

    // intitiating dbhelper variable
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    // throwing an exception if does not get a writable database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // closing the db
    public void close() {
        dbHelper.close();
    }

    // Creating the value of comment
    public Comment createComment(String comment, String rating) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);

        // from farah
        values.put(MySQLiteHelper.COLUMN_RATING, rating);

        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);

        //Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
        //allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        // null, null, null);

        // from farah
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, null, null, null, null, null, null);

        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    // Deleting the comment value
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    // Add all comments into the list
    public List<Comment> getAllComments() {

        // Creating an object of comment for the type of array list
        List<Comment> comments = new ArrayList<Comment>();

        // Creating an object of cursor holding all the table comments
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        // Moving the fist element of the cursor object
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    // Getting comment
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));

        // from farah
        // the app started crashing after i added the below line
       // comment.setRating(cursor.getString(cursor.getColumnIndex( MySQLiteHelper.COLUMN_RATING )));
        comment.setRating(cursor.getString( cursor.getColumnIndex( MySQLiteHelper.COLUMN_RATING ) ));
        // comment.setRate(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_COMMENT)));

        // comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}