package com.example.m5waitlistv3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class WaitListCursorAdapter extends CursorAdapter {

    public WaitListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.studentcell, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //init views
        TextView tvcourse = view.findViewById(R.id.cellcourse);
        TextView tvstudentname = view.findViewById(R.id.cellstudentname);
        TextView tvpriority = view.findViewById(R.id.cellpriority);

        String name = cursor.getString(cursor.getColumnIndexOrThrow("studentname"));
        String course = cursor.getString(cursor.getColumnIndexOrThrow("courseid"));;
        String priority = cursor.getString(cursor.getColumnIndexOrThrow("priority"));;

        //set text of views using getters from Student class
        tvcourse.setText(course);
        tvstudentname.setText(name);
        tvpriority.setText(priority);

    }
}
