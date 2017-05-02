package com.example.root.prepolymp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.prepolymp.R;

public class Stats extends Fragment {

    public static int att_alg = 0;
    public static int att_comb = 0;
    public static int att_geom = 0;

    public static int cor_alg = 0;
    public static int cor_comb = 0;
    public static int cor_geom = 0;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_stats, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Статистика");

        TextView tv1 = (TextView)view.findViewById(R.id.stats1);
        tv1.setText("Решено " + totalCor());

        TextView tv2 = (TextView)view.findViewById(R.id.stats2);
        tv2.setText("Израсходовано " + totalAtt() + " попыток");


        TextView tv3 = (TextView)view.findViewById(R.id.stats3);
        tv3.setText(Html.fromHtml("Алгебра: <font color=#C162EA>" + cor_alg + "</font> из <font color=#0000FF>" + att_alg + "</font> попыток."));

        TextView tv4 = (TextView)view.findViewById(R.id.stats4);
        tv4.setText("Комбинаторика: " + cor_comb + " из " + att_comb + " попыток.");

        TextView tv5 = (TextView)view.findViewById(R.id.stats5);
        tv5.setText("Геометрия: " + cor_geom + " из " + att_geom + " попыток.");
    }

    public int totalAtt() {
        return att_alg + att_comb + att_geom;
    }

    public static int totalCor() {
        return cor_alg + cor_comb + cor_geom;
    }
}
