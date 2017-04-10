package com.example.root.prepolymp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Start extends AppCompatActivity {

    public static ArrayList<Problem> problems = new ArrayList<>();
    public static ArrayList<Boolean> isFavourite = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Problem problem = new Problem(1, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "алгебра");
        problems.add(problem);
        isFavourite.add(false);
        problems.add(new Problem(2, "Посчитайте 2 + 3", "5", "алгебра"));
        isFavourite.add(false);
        problems.add(new Problem(3, "Посчитайте 25 + 3", "28", "алгебра"));
        isFavourite.add(false);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
