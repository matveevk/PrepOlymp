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
        TextView tv2 = (TextView)findViewById(R.id.problemForm);
        tv2.setText("Класс: " + problem.form);
        TextView tv3 = (TextView)findViewById(R.id.problemDiff);
        tv3.setText("Сложность: " + problem.diff);
        TextView tv4 = (TextView)findViewById(R.id.problemTopic);
        tv4.setText("Тема: " + problem.topic);

    }

    public int screenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
