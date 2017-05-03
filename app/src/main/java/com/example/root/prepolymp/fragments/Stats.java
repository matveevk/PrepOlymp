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
        tv1.setText("Всего решено: " + totalCor());

        TextView tv2 = (TextView)view.findViewById(R.id.stats2);
        tv2.setText("Всего попыток: " + totalAtt());


        TextView tv6 = (TextView)view.findViewById(R.id.stats6);
        tv6.setText(Html.fromHtml("<font color=" + R.color.colorAlg + ">" + cor_alg
                + "</font> из <font color=" + R.color.colorAlg + ">" + att_alg
                + "</font> попыток, " + "<font color=" + R.color.colorAlg + ">"
                + (att_alg > 0 ? 100 * cor_alg / att_alg : 0) + "%"));

        TextView tv7 = (TextView)view.findViewById(R.id.stats7);
        tv7.setText(Html.fromHtml("<font color=" + R.color.colorComb + ">" + cor_comb
                + "</font> из <font color=" + R.color.colorComb + ">" + att_comb
                + "</font> попыток, " + "<font color=" + R.color.colorComb + ">"
                + (att_comb > 0 ? 100 * cor_comb / att_comb : 0) + "%"));

        TextView tv8 = (TextView)view.findViewById(R.id.stats8);
        tv8.setText(Html.fromHtml("<font color=" + R.color.colorGeom + ">" + cor_geom
                + "</font> из <font color=" + R.color.colorGeom + ">" + att_geom
                + "</font> попыток, " + "<font color=" + R.color.colorGeom + ">"
                + (att_geom > 0 ? 100 * cor_geom / att_geom : 0) + "%"));
    }

    public int totalAtt() {
        return att_alg + att_comb + att_geom;
    }

    public static int totalCor() {
        return cor_alg + cor_comb + cor_geom;
    }
}
