package com.example.root.prepolymp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.TextView;

import static com.example.root.prepolymp.Storage.problems;

public class ProblemInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_info);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int problemId = intent.getIntExtra(ProblemActivity.EXTRA_MESSAGE, 1);
        Problem problem = problems.get(problemId - 1);

        TextView tv5 = (TextView)findViewById(R.id.problemTextInfo);
        tv5.setWidth(screenWidth() / 10 * 9);
        tv5.setText(problem.text);

        TextView tv1 = (TextView)findViewById(R.id.problemNo);
        tv1.setText("Задача № " + problem.id);
        switch(problem.topic) {
            case "алгебра": tv1.setTextColor(getResources().getColor(R.color.colorAlg)); break;
            case "комбинаторика": tv1.setTextColor(getResources().getColor(R.color.colorComb)); break;
            case "геометрия": tv1.setTextColor(getResources().getColor(R.color.colorGeom)); break;
        }
        TextView tv2 = (TextView)findViewById(R.id.problemForm);
        tv2.setText("Класс: " + problem.form);
        TextView tv3 = (TextView)findViewById(R.id.problemDiff);
        tv3.setText("Сложность: " + problem.diff);
        TextView tv4 = (TextView)findViewById(R.id.problemTopic);
        tv4.setText("Тема: " + problem.topic);
        TextView tv6 = (TextView)findViewById(R.id.problemOrigins);
        tv6.setWidth(screenWidth() / 10 * 9);
        tv6.setText("Источник: " + problem.origins);
        switch(problem.topic) {
            case "алгебра": tv6.setTextColor(getResources().getColor(R.color.colorAlg)); break;
            case "комбинаторика": tv6.setTextColor(getResources().getColor(R.color.colorComb)); break;
            case "геометрия": tv6.setTextColor(getResources().getColor(R.color.colorGeom)); break;
        }
    }

    public int screenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}