package com.example.root.prepolymp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Start extends AppCompatActivity {

    public static ArrayList<Problem> problems = new ArrayList<>();
    public static ArrayList<Boolean> isFavourite = new ArrayList<>();
    public static ArrayList<Boolean> isLater = new ArrayList<>();
    public static ArrayList<Boolean> isSolved = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Problem problem = new Problem(1, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "алгебра");
        problems.add(problem);
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(2, "Посчитайте 25 + 3", "28", "алгебра", 10));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(3, "Посчитайте 25 + 3", "28", "геометрия", 11));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(4, "Посчитайте 25 + 3", "28", "алгебра", 9));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(5, "Посчитайте 25 + 3", "28", "комбинаторика", 8));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(6, "Посчитайте 25 + 3", "28", "геометрия", 10));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(7, "Посчитайте 25 + 3", "28", "алгебра", 10));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(8, "Посчитайте 25 + 3", "28", "комбинаторика", 11));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(9, "Посчитайте 25 + 3", "28", "комбинаторика", 10));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(10, "Посчитайте 25 + 3", "28", "геометрия", 10));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(11, "Посчитайте 25 + 3", "28", "алгебра", 8));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(12, "Посчитайте 25 + 3", "28", "геометрия", 10));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(13, "Посчитайте 25 + 3", "28", "геометрия", 9));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        problems.add(new Problem(14, "Посчитайте 25 + 3", "28", "алгебра", 8));
        isFavourite.add(false);
        isLater.add(false);
        isSolved.add(false);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
