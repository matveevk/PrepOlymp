package com.example.root.prepolymp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.root.prepolymp.fragments.About;
import com.example.root.prepolymp.fragments.Favourites;
import com.example.root.prepolymp.fragments.ProblemList;
import com.example.root.prepolymp.fragments.RandomContest;
import com.example.root.prepolymp.fragments.Settings;
import com.example.root.prepolymp.fragments.SolveLater;
import com.example.root.prepolymp.fragments.Solved;
import com.example.root.prepolymp.fragments.Stats;

import static com.example.root.prepolymp.ProblemActivity.navHeaderUpdate;
import static com.example.root.prepolymp.Storage.firstname;
import static com.example.root.prepolymp.Storage.lastname;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  // for keyboard not to inflate

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView tv = (TextView) header.findViewById(R.id.nav_text_view1);
        tv.setText(firstname + " " + lastname);

        navHeaderUpdate();

        displaySelectedFragment(R.id.nav_problem_list);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d("ddd", "2");
        getMenuInflater().inflate(R.menu.nav_drawer_problems, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Log.d("ddd", "3");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_problems, new Settings());
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Log.d("ddd", "4");
        displaySelectedFragment(id);

        return true;
    }

    private void displaySelectedFragment(int id) {

        Log.d("ddd", "5");
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_problem_list:
                fragment = new ProblemList();
                break;
            case R.id.nav_favourites:
                fragment = new Favourites();
                break;
            case R.id.nav_solve_later:
                fragment = new SolveLater();
                break;
            case R.id.nav_solved:
                fragment = new Solved();
                break;
            case R.id.nav_random_contest:
                fragment = new RandomContest();
                break;
            case R.id.nav_stats:
                fragment = new Stats();
                break;
            case R.id.nav_about:
                fragment = new About();
                break;
            case R.id.nav_settings:
                fragment = new Settings();
                break;
        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_problems, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}