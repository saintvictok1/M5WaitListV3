package com.example.m5waitlistv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class StudentManagerActivity extends AppCompatActivity {
    Button backbtn, addbtn;
    Spinner studentcoursespn, studentpriorityspn;
    EditText studentnameET;
    ArrayAdapter<String> PrioritySpnpadapter, CourseSpnadaper;
    String[] Priority;
    String[] Course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);
        initViews();
        setOnClickListener();
    }


    public void initViews() {
        backbtn = findViewById(R.id.studententrybackbtn);
        addbtn = findViewById(R.id.addstudententry);
        studentcoursespn = findViewById(R.id.StudentCourseSPN);
        studentpriorityspn = findViewById(R.id.StudentPrioritySPN);
        studentnameET = findViewById(R.id.StudentNameET);
        Course = new String[]{"CSIT555", "CSIT561", "CSIT537"};
        Priority = new String[]{"5 Graduate", "4 Senior", "3 Junior", "2 Sophomore", "1 Freshman"};
        CourseSpnadaper = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,Course);
        PrioritySpnpadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,Priority);
        studentpriorityspn.setAdapter(PrioritySpnpadapter);
        studentcoursespn.setAdapter(CourseSpnadaper);

    }
    public void GoWaitList() {
        Intent gotowaitlist = new Intent(this, MainActivity.class);
        startActivity(gotowaitlist);
    }
    public void setOnClickListener() {
        //add
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                addStudenttoDB();
            }
        });
        //back to homescreen if you dont want to add entry
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoWaitList();
            }
        });
    }

    public void addStudenttoDB(){
        WaitListDbHelper DB = new WaitListDbHelper(StudentManagerActivity.this);
        String courseid, name, priority;
        courseid = studentcoursespn.getSelectedItem().toString();
        name = studentnameET.getText().toString().trim();
        priority = studentpriorityspn.getSelectedItem().toString();
        DB.addStudentToDB(name,courseid,priority);
        GoWaitList();
        finish();

    }
}