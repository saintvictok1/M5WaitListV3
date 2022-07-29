package com.example.m5waitlistv3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class WaitListDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WAITLIST.DB";
    private static final String SQL_CREATESTUDENT_ENTRIES =
            "CREATE TABLE " + WaitListContract.StudentEntry.TABLE_NAME + " (" +
                    WaitListContract.StudentEntry._ID + " INTEGER PRIMARY KEY," +
                    WaitListContract.StudentEntry.COLUMN_NAME_COURSEID + " TEXT," +
                    WaitListContract.StudentEntry.COLUMN_NAME_STUDENTNAME + " TEXT," +
                    WaitListContract.StudentEntry.COLUMN_NAME_PRIORITY + " TEXT)";

    private static final String SQL_DELETESTUDENT_ENTRIES =
            "DROP TABLE IF EXISTS " + WaitListContract.StudentEntry.TABLE_NAME;

    public WaitListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(SQL_CREATECOURSE_ENTRIES);
        db.execSQL(SQL_CREATESTUDENT_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETESTUDENT_ENTRIES);
        onCreate(db);
    }

    public void addStudentToDB(String name, String courseid, String priority) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(WaitListContract.StudentEntry.COLUMN_NAME_COURSEID, courseid);
        values.put(WaitListContract.StudentEntry.COLUMN_NAME_STUDENTNAME, name);
        values.put(WaitListContract.StudentEntry.COLUMN_NAME_PRIORITY, priority);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(WaitListContract.StudentEntry.TABLE_NAME, null, values);
    }

   // private ArrayList<String> studentList = getStudentsFromDB();

    public Cursor getAllStudents() {
        String selectQuery = "SELECT  * FROM "+ WaitListContract.StudentEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
    public ArrayList<String> getStudentsFromDB(){
        ArrayList<String> resultList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT" +;
        //Define a projection specifying which columns will be

        String[] projection = {
                BaseColumns._ID,
                WaitListContract.StudentEntry.COLUMN_NAME_COURSEID,
                WaitListContract.StudentEntry.COLUMN_NAME_STUDENTNAME,
                WaitListContract.StudentEntry.COLUMN_NAME_PRIORITY,
        };
        //Filter results WHERE "studentname" = 'student name'
        String selection = WaitListContract.StudentEntry.COLUMN_NAME_STUDENTNAME + " = ?";
        String[] selectionArgs = { "My"};

        String sortOrder = WaitListContract.StudentEntry.COLUMN_NAME_PRIORITY + " DESC";

        Cursor cursor = db.query(
                WaitListContract.StudentEntry.TABLE_NAME, //TableName
                projection,                               //Array of Columns to return
                selection,                                //columns for the WHERE clause
                selectionArgs,                            //values for the WHERE clause
                null,                             //dont group rows
                null,                              //dont filter by row groups
                sortOrder                                 //The sort order
                );
        return null;
    }

}
