package com.example.root.prepolymp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.prepolymp.R;

import static com.example.root.prepolymp.Storage.firstname;
import static com.example.root.prepolymp.Storage.lastname;

public class Settings extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Настройки");

        final EditText fname = (EditText) getActivity().findViewById(R.id.firstnameInput);
        final EditText lname = (EditText) getActivity().findViewById(R.id.lastnameInput);
        if (!firstname.equals("Имя")) {
            fname.setText(firstname);
        }
        if (!lastname.equals("Фамилия")) {
            lname.setText(lastname);
        }

        Button button = (Button) getActivity().findViewById(R.id.saveNames);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstname = fname.getText().toString();
                lastname = lname.getText().toString();
                Toast.makeText(getActivity(), "Сохранено", Toast.LENGTH_SHORT).show();
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                View header = navigationView.getHeaderView(0);
                TextView tv = (TextView)header.findViewById(R.id.nav_text_view1);
                tv.setText(firstname + " " + lastname);
            }
        });
    }

    public int screenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
