package com.example.root.prepolymp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static com.example.root.prepolymp.Storage.problems;

public class Start extends AppCompatActivity {

    //static DBManager dbProblems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Log.d("abc", "3");
        DBManager dbProblems = new DBManager(this);
        dbProblems.getWritableDatabase();

        Log.d("abc", "2");

        if (dbProblems.size() == 0) {
            dbProblems.addProblem(new Problem(1, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "алгебра"));
            dbProblems.addProblem(new Problem(3, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "геометрия", 11));
            dbProblems.addProblem(new Problem(2, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "алгебра", 10));
        }

        problems = dbProblems.getAllProblems();
        for (Problem problem : problems) {
            if (!problem.solved) {
                continue;
            }
            //if (problem.topic = "алгебра")
        }
        Log.d("problem1", problems.get(0).topic);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
