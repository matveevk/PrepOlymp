package com.example.root.prepolymp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper{

    public static final String DB_NAME = "problems.db";
    public static final String TABLE_NAME = "Problems";
    public static final String COL_ID = "ID";
    public static final String COL_SITUATION = "SITUATION";
    public static final String COL_ANSWER = "ANSWER";
    public static final String COL_TOPIC = "TOPIC";
    public static final String COL_FORM = "FORM";
    public static final String COL_DIFFICULTY = "DIFFICULTY";
    public static final String COL_ORIGINS = "ORIGINS";
    public static final String COL_LIKED = "LIKED";
    public static final String COL_MARKED = "MARKED";
    public static final String COL_SOLVED = "SOLVED";

    SQLiteDatabase db;

    public DBManager(Context context) {
        super(context, DB_NAME, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("abc", "1");
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SITUATION + " TEXT, "
                + COL_ANSWER + " TEXT, "
                + COL_TOPIC + " TEXT, "
                + COL_FORM + " INTEGER, "
                + COL_DIFFICULTY + " INTEGER, "
                + COL_ORIGINS + " TEXT, "
                + COL_LIKED + " INTEGER, "
                + COL_MARKED + " INTEGER, "
                + COL_SOLVED + " INTEGER" + ");";
        Log.d("query", query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addProblem(Problem problem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, problem.id);
        cv.put(COL_SITUATION, problem.text);
        cv.put(COL_ANSWER, problem.ans);
        cv.put(COL_TOPIC, problem.topic);
        cv.put(COL_FORM, problem.form);
        cv.put(COL_DIFFICULTY, problem.diff);
        cv.put(COL_ORIGINS, problem.origins);
        cv.put(COL_LIKED, problem.liked ? 1 : 0);
        cv.put(COL_MARKED, problem.marked ? 1 : 0);
        cv.put(COL_SOLVED, problem.solved ? 1 : 0);
        db.insert(TABLE_NAME, null, cv);
    }

    public void updateProblem(Problem problem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, problem.id);
        cv.put(COL_SITUATION, problem.text);
        cv.put(COL_ANSWER, problem.ans);
        cv.put(COL_TOPIC, problem.topic);
        cv.put(COL_FORM, problem.form);
        cv.put(COL_DIFFICULTY, problem.diff);
        cv.put(COL_ORIGINS, problem.origins);
        cv.put(COL_LIKED, problem.liked ? 1 : 0);
        cv.put(COL_MARKED, problem.marked ? 1 : 0);
        cv.put(COL_SOLVED, problem.solved ? 1 : 0);
        db.update(TABLE_NAME, cv, COL_ID + " = ?", new String[] {String.valueOf(problem.id)});
    }

    public int size() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        Log.d("size", "" + cursor.getCount());
        return cursor.getCount();
    }

    public ArrayList<Problem> getAllProblems() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Problem> res = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Problems;", null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID)));
            String text = cursor.getString(cursor.getColumnIndex(COL_SITUATION));
            String ans = cursor.getString(cursor.getColumnIndex(COL_ANSWER));
            String topic = cursor.getString(cursor.getColumnIndex(COL_TOPIC));
            int form = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_FORM)));
            int diff = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_DIFFICULTY)));
            String origins = cursor.getString(cursor.getColumnIndex(COL_ORIGINS));
            boolean liked = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_LIKED))) == 1 ? true : false;
            boolean marked = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_MARKED))) == 1 ? true : false;
            boolean solved = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_SOLVED))) == 1 ? true : false;
            res.add(new Problem(id, text, ans, topic, form, diff, origins, liked, marked, solved));
        }
        return res;
    }
}
