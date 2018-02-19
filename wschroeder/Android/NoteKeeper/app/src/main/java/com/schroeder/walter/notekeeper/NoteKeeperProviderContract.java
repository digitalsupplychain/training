package com.schroeder.walter.notekeeper;

import android.content.IntentFilter;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by wschroeder on 14/02/2018.
 */

public final class NoteKeeperProviderContract {
    private NoteKeeperProviderContract(){}
    public static final String AUTHORITY = "com.schroeder.walter.notekeeper.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    protected interface CoursesIdColums{
        public static final String COLUMN_COURSE_ID = "course_id";
    }

    protected interface CourseColumns {
        public static final String COLUMN_COURSE_TITLE = "course_title";
    }

    protected interface NotesColumns {
        public static final String COLUMN_NOTE_TITLE = "note_title";
        public static final String COLUMN_NOTE_TEXT = "note_text";
    }

    public static final class Courses implements BaseColumns, CourseColumns, CoursesIdColums{
        public static final String PATH = "courses";
//      content://com.schroeder.walter.notekeeper/courses
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
    }

    public static final class Notes implements BaseColumns, NotesColumns, CoursesIdColums, CourseColumns{
        public static final String PATH = "notes";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
        public static final String PATH_EXPANDED = "notes_expanded";
        public static final Uri CONTENT_EXPANDED_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH_EXPANDED);

    }
}