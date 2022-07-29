package com.example.m5waitlistv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addstudent;
    private ListView StudentListView;

    //private ArrayList<String> studentList = getStudentsFromDB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        RetrieveStudents();
    }

    public void initViews(){
        StudentListView = findViewById(R.id.studentListView);
        addstudent = findViewById(R.id.addstudent);
        setOnClickListener();

    }



    //onclicklistener for add student button (+)
    public void setOnClickListener() {
        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToNewStudent();
            }
        });
        StudentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Cursor cursor = WaitListCursorAdapter.
            }
        });

    }
    public void GoToNewStudent() {
        Intent newStudent = new Intent(this, StudentManagerActivity.class);
        startActivity(newStudent);
    }


    //retrieve List of Students from Database
    public void RetrieveStudents(){
        WaitListDbHelper dbHelper = new WaitListDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Student ORDER BY priority DESC, courseid";
        Cursor cursor = db.rawQuery(query, null);

        //setup cursor adapter
        WaitListCursorAdapter adapter = new WaitListCursorAdapter(
                this, cursor);
        //attach cursor adapter to listview
        StudentListView.setAdapter(adapter);
    }
}