package com.example.root.prepolymp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.prepolymp.R;

public class About extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("О приложении");

        TextView tv2 = (TextView)view.findViewById(R.id.menu_about2);
        tv2.setWidth(screenWidth() / 100 * 88);
        String s2 = "Эта версия приложения PrepOlymp - платформа для решения олимпиадных задач по математике." +
                " Здесь вы можете тренироваться на задачах разного уровня, чтобы оттачивать мастерство и готовиться к олимпиадам.";
        tv2.setText(s2);

        TextView tv4 = (TextView)view.findViewById(R.id.menu_about4);
        tv4.setWidth(screenWidth() / 100 * 88);
        String s4 = "Выберите понравившуюся задачу из базы задач. Когда вы её решили, введите ответ в данном окне и нажмите \"Проверить\". Система покажет вам вердикт. Если ваш ответ неверный, вы сможете отправить другой ответ.\n" +
                "Каждая ваша попытка учитывается в разделе статистики. Там вы можете увидеть, насколько хорошо у вас получается та или иная тема, над чем вам надо усиленно работать.\n" +
                "Для того, чтобы не терять понравившиеся и уже начатые задачи, вы можете добавить их в соответствующие списки. Это можно сделать в правом верхнем углу в режиме решения.";
        tv4.setText(s4);

        TextView tv6 = (TextView)view.findViewById(R.id.menu_about6);
        tv6.setWidth(screenWidth() / 100 * 88);
        String s6 = "Я - автор этого приложения, Матвеев Константин. При возникновении вопросов или пожеланий свяжитесь со мной по электронной почте matveev.kp@mail.ru";
        tv6.setText(s6);

        TextView tv7 = (TextView)view.findViewById(R.id.menu_about7);
        tv7.setWidth(screenWidth() / 100 * 88);
        String s7 = "Приложение PrepOlymp. \n" +
                "Версия 0 (alpha).\n" +
                "(с) 2017 Матвеев Константин. ";
        tv7.setText(s7);
    }

    public int screenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
