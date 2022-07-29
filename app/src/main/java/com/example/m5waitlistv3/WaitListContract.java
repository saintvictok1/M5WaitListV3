package com.example.m5waitlistv3;

import android.provider.BaseColumns;

public final class WaitListContract {

    private WaitListContract(){
    }

    /* Inner class that defines the table contents */
    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "Student";
        public static final String COLUMN_NAME_COURSEID = "courseid";
        public static final String COLUMN_NAME_STUDENTNAME = "studentname";
        public static final String COLUMN_NAME_PRIORITY = "priority";

    }




}


