package com.example.root.prepolymp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.root.prepolymp.fragments.Stats;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.root.prepolymp.Storage.firstname;
import static com.example.root.prepolymp.Storage.lastname;
import static com.example.root.prepolymp.Storage.problems;
import static com.example.root.prepolymp.fragments.Favourites.adapterFavourites;
import static com.example.root.prepolymp.fragments.ProblemList.adapterAllProblems;
import static com.example.root.prepolymp.fragments.SolveLater.adapterMarked;
import static com.example.root.prepolymp.fragments.Solved.adapterSolved;

public class Start extends AppCompatActivity {

    public static final String NAMES_FILE_NAME = "PrepOlympNames";
    public static final String ATTEMPTS_FILE_NAME = "PrepOlympAttempts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        DBManager dbProblems = new DBManager(this);
        dbProblems.getWritableDatabase();

        if (dbProblems.size() == 0) {
            dbProblems.addProblem(new Problem(1, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "алгебра"));
            dbProblems.addProblem(new Problem(3, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "геометрия", 11));
            dbProblems.addProblem(new Problem(2, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "алгебра", 10));
            dbProblems.addProblem(new Problem(5, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "алгебра"));
            dbProblems.addProblem(new Problem(6, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "геометрия", 11));
            dbProblems.addProblem(new Problem(4, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "алгебра", 10));
        }

        problems = dbProblems.getAllProblems();
        for (Problem problem : problems) {
            if (!problem.solved) {
                continue;
            }
            switch(problem.topic) {
                case "алгебра": ++Stats.cor_alg; break;
                case "комбинаторика": ++Stats.cor_comb; break;
                case "геометрия": ++Stats.cor_geom; break;
            }
        }
        Log.d("problem1", problems.get(0).topic);

        fixArrayAdapterBug();
        getUserName();
        getAttemptsNums();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void fixArrayAdapterBug() {
        // The following paragraph fixes a bug (nullPointerException with arrayAdapters)
        // It should not be minded
        ArrayList<String> bugFix = new ArrayList<>();
        adapterAllProblems = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1,bugFix);
        adapterFavourites = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1,bugFix);
        adapterMarked = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1,bugFix);
        adapterSolved = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1,bugFix);

    }

    public void getUserName() {
        InputStream inputStream = null;
        try {
            inputStream = openFileInput(NAMES_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder1 = new StringBuilder();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder1.append(bufferedReader.readLine());  // firstname
                stringBuilder2.append(bufferedReader.readLine());  // lastname
                firstname = stringBuilder1.toString();
                lastname = stringBuilder2.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAttemptsNums() {
        InputStream inputStream = null;
        try {
            inputStream = openFileInput(ATTEMPTS_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder1 = new StringBuilder();
                StringBuilder stringBuilder2 = new StringBuilder();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder1.append(bufferedReader.readLine());  // alg
                stringBuilder2.append(bufferedReader.readLine());  // comb
                stringBuilder3.append(bufferedReader.readLine());  // geom
                Stats.att_alg = Integer.parseInt(stringBuilder1.toString());
                Stats.att_comb = Integer.parseInt(stringBuilder2.toString());
                Stats.att_geom = Integer.parseInt(stringBuilder3.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
